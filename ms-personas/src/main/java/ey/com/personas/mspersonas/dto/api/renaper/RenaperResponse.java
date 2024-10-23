package ey.com.personas.mspersonas.dto.api.renaper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record RenaperResponse(@JsonProperty("response") List<Response> response)
    implements Serializable {
  @Serial private static final long serialVersionUID = 2261481732154775553L;
}
