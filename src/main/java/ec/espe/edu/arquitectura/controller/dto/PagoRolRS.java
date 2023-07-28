package ec.espe.edu.arquitectura.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class PagoRolRS {
    private String mes;
    private String rucEmpresa;
    private BigDecimal valorReal;
    private BigDecimal valorTotal;
    private Integer totalTransacciones;
    private Integer errores;
}
