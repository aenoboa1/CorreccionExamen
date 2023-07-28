package ec.espe.edu.arquitectura.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpresaRQ {
    private String ruc;
    private String razonSocial;
    private String cuentaPrincipal;
    private List<EmpleadoRQ> empleados;
}
