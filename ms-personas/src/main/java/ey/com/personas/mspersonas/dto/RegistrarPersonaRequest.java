package ey.com.personas.mspersonas.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    private String dni;

    @NotEmpty
    private String calle;

    @NotEmpty
    private String numero;

    @NotEmpty
    private String provincia;

    @NotEmpty
    private String localidad;

    @NotNull
    private Double sueldoBruto;
}
