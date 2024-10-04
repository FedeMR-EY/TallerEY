package ey.com.personas.mspersonas.dto.api.veraz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class VerazResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -2197664558198423102L;

    @JsonProperty("response")
    private List<Response> response;
}
