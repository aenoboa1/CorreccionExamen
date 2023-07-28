package ec.espe.edu.arquitectura.controller.mapper;


import ec.espe.edu.arquitectura.controller.dto.EmpleadoRQ;
import ec.espe.edu.arquitectura.controller.dto.EmpresaRQ;
import ec.espe.edu.arquitectura.model.Empleado;
import ec.espe.edu.arquitectura.model.Empresa;

public class EmpresaMapper {

    public static Empresa empresaRQtoEmpresa(EmpresaRQ empresaRQ) {
        return Empresa.builder()
                .ruc(empresaRQ.getRuc())
                .razonSocial(empresaRQ.getRazonSocial())
                .cuentaPrincipal(empresaRQ.getCuentaPrincipal())
                .build();
    }
    public static Empleado empleadoRQtoEmpleado(EmpleadoRQ empleadoRQ) {
        return Empleado.builder()
                .cedulaDeIdentidad(empleadoRQ.getCedulaDeIdentidad())
                .nombres(empleadoRQ.getNombres())
                .numeroDeCuenta(empleadoRQ.getNumeroDeCuenta())
                .build();
    }
}
