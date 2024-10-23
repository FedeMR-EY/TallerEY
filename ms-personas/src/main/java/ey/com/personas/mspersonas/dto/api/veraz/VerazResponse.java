package ey.com.personas.mspersonas.dto.api.veraz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record VerazResponse(@JsonProperty("response") List<Response> response)
    implements Serializable {
  @Serial private static final long serialVersionUID = -2197664558198423102L;
}
