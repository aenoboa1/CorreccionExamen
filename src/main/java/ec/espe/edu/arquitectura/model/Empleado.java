package ec.espe.edu.arquitectura.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Empleado {
    private String cedulaDeIdentidad;
    private String apellidos;
    private String nombres;
    private String numeroDeCuenta;
}
