package ey.com.personas.mspersonas.dto.api.renaper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record Response(
    @JsonProperty("dni") long dni, @JsonProperty("isAuthorize") boolean isAuthorize)
    implements Serializable {
  @Serial private static final long serialVersionUID = 2752887417919332861L;
}
