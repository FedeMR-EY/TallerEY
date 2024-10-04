package ey.com.personas.mspersonas.dto.api.veraz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = -7268740336357481989L;

    @JsonProperty("dni")
    private long dni;

    @JsonProperty("score")
    private double score;
}