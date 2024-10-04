package ey.com.personas.mspersonas.dto.api.worldsys;

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
public class WorldSysResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 2662955281858973445L;

    @JsonProperty("response")
    private List<Response> response;
}
