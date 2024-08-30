package ey.com.personas.mspersonas.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrarPersonaRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5759989610614293004L;

    private String nombre;
    private String apellido;
    private String dni;
    private String calle;
    private String numero;
    private String provincia;
    private String localidad;
}
