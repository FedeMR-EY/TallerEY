package ey.com.personas.mspersonas.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegistrarPersonaRequest(
    @NotEmpty String nombre,
    @NotEmpty String apellido,
    @NotEmpty String dni,
    @NotEmpty String calle,
    @NotEmpty String numero,
    @NotEmpty String provincia,
    @NotEmpty String localidad,
    @NotNull Double sueldoBruto)
    implements Serializable {
  @Serial private static final long serialVersionUID = 5759989610614293004L;
}
