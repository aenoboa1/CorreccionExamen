package ec.edu.espe.examen.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EmpleadoPago {
    private String numeroCuenta;
    private BigDecimal valor;
    private String estado;

}
