package ec.espe.edu.arquitectura.service;

import ec.espe.edu.arquitectura.controller.dto.EmpleadoPagoRQ;
import ec.espe.edu.arquitectura.controller.dto.PagoRolRQ;
import ec.espe.edu.arquitectura.controller.dto.PagoRolRS;
import ec.espe.edu.arquitectura.controller.mapper.PagoRolMapper;
import ec.espe.edu.arquitectura.model.Empleado;
import ec.espe.edu.arquitectura.model.EmpleadoPago;
import ec.espe.edu.arquitectura.model.Empresa;
import ec.espe.edu.arquitectura.model.PagoRol;
import ec.espe.edu.arquitectura.repository.EmpresaRepository;
import ec.espe.edu.arquitectura.repository.PagoRolRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagoRolService {
    private final PagoRolRepository pagoRolRepository;
    private final EmpresaRepository empresaRepository;

    public PagoRolService(PagoRolRepository pagoRolRepository, EmpresaRepository empresaRepository) {
        this.pagoRolRepository = pagoRolRepository;
        this.empresaRepository = empresaRepository;
    }

    public PagoRolRS validatePagoRol(String mes, String rucEmpresa) {
        Optional<Empresa> empresa = this.empresaRepository.findByRuc(rucEmpresa);
        if (empresa.isPresent()) {
            Optional<PagoRol> pagoRol = this.pagoRolRepository.findByMesAndRucEmpresa(mes, rucEmpresa);
            BigDecimal valorReal = BigDecimal.ZERO;
            Integer totalTransaccciones = 0;
            Integer errores = 0;
            if (pagoRol.isPresent()) {
                List<EmpleadoPago> empleadosPagos = pagoRol.get().getEmpleadosPago();
                for (EmpleadoPago empleadoPago : empleadosPagos) {
                    Optional<Empleado> empleado = empresa.get().getEmpleados().stream().filter(e -> e.getNumeroDeCuenta().equals(empleadoPago.getNumeroCuenta())).findFirst();
                    if (empleado.isPresent()) {
                        empleadoPago.setEstado("VAL");
                        valorReal = valorReal.add(empleadoPago.getValor());
                        totalTransaccciones++;
                    } else {
                        empleadoPago.setEstado("BAD");
                        errores++;
                    }
                }
                pagoRol.get().setValorReal(valorReal);
                this.pagoRolRepository.save(pagoRol.get());
                return PagoRolRS.builder()
                        .mes(mes)
                        .rucEmpresa(rucEmpresa)
                        .valorTotal(pagoRol.get().getValorTotal())
                        .totalTransacciones(totalTransaccciones)
                        .valorReal(valorReal)
                        .errores(errores)
                        .build();
            } else {
                throw new RuntimeException("No existe un rol de pago para el mes y la empresa");
            }
        } else {
            throw new RuntimeException("No existe una empresa con ese ruc");
        }

    }

    public void createPagoRolMensual(PagoRolRQ pagoRolRQ) {
        PagoRol pagoRol = PagoRolMapper.PagoRolRQtoPagoRol(pagoRolRQ);
        Optional<PagoRol> pagoRolOpt = this.pagoRolRepository.findByMesAndRucEmpresa(pagoRol.getMes(), pagoRol.getRucEmpresa());
        if (!pagoRolOpt.isPresent()) {
            BigDecimal sum = BigDecimal.ZERO;
            List<EmpleadoPago> empleadosPagos = new ArrayList<>();
            for (EmpleadoPagoRQ empleadosPagoRQ : pagoRolRQ.getEmpleadosPago()) {
                EmpleadoPago empleadoPago = PagoRolMapper.EmpleadoPagoRQtoEmpleadoPago(empleadosPagoRQ);
                empleadoPago.setEstado("PEN");
                sum = sum.add(empleadoPago.getValor());
                empleadosPagos.add(empleadoPago);
            }
            pagoRol.setEmpleadosPago(empleadosPagos);
            pagoRol.setValorTotal(sum);
            pagoRol.setValorReal(BigDecimal.ZERO);
            this.pagoRolRepository.save(pagoRol);
        } else {
            throw new RuntimeException("Ya existe un rol de pago para el mes y la empresa");
        }
    }
}
