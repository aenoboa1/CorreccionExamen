package ec.espe.edu.arquitectura.controller.mapper;


import ec.espe.edu.arquitectura.controller.dto.EmpleadoPagoRQ;
import ec.espe.edu.arquitectura.controller.dto.PagoRolRQ;
import ec.espe.edu.arquitectura.controller.dto.PagoRolRS;
import ec.espe.edu.arquitectura.model.EmpleadoPago;
import ec.espe.edu.arquitectura.model.PagoRol;

public class PagoRolMapper {

    public static PagoRol PagoRolRQtoPagoRol(PagoRolRQ pagoRolRQ) {
        return PagoRol.builder()
                .mes(pagoRolRQ.getMes())
                .rucEmpresa(pagoRolRQ.getRucEmpresa())
                .cuentaPrincipal(pagoRolRQ.getCuentaPrincipal())
                .build();
    }
    public static EmpleadoPago EmpleadoPagoRQtoEmpleadoPago(EmpleadoPagoRQ empleadoPago) {
        return EmpleadoPago.builder()
                .numeroCuenta(empleadoPago.getNumeroCuenta())
                .valor(empleadoPago.getValor())
                .build();
    }

}
