package ec.espe.edu.arquitectura.service;

import ec.espe.edu.arquitectura.controller.dto.EmpleadoRQ;
import ec.espe.edu.arquitectura.controller.dto.EmpresaRQ;
import ec.espe.edu.arquitectura.controller.mapper.EmpresaMapper;
import ec.espe.edu.arquitectura.model.Empleado;
import ec.espe.edu.arquitectura.model.Empresa;
import ec.espe.edu.arquitectura.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa createEmpresa(EmpresaRQ empresaRQ) {
        Empresa empresaOpt = EmpresaMapper.empresaRQtoEmpresa(empresaRQ);
        Optional<Empresa> empresa = this.empresaRepository.findByRuc(empresaOpt.getRuc());
        if (empresa.isPresent()) {
            throw new RuntimeException("Ya existe una empresa con ese ruc");
        } else {
            List<Empleado> empleados = new ArrayList<>();
            for (EmpleadoRQ empleadosRQ : empresaRQ.getEmpleados()) {
                empleados.add(EmpresaMapper.empleadoRQtoEmpleado(empleadosRQ));
            }
            empresaOpt.setEmpleados(empleados);
            return this.empresaRepository.save(empresaOpt);
        }
    }
}
