package ey.com.personas.mspersonas.dto.api.worldsys;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record Response(
    @JsonProperty("dni") long dni, @JsonProperty("isTerrotist") boolean isTerrotist)
    implements Serializable {
  @Serial private static final long serialVersionUID = 7763084406269513874L;
}
