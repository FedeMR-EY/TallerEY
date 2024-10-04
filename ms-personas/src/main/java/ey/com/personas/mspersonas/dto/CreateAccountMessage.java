package ey.com.personas.mspersonas.dto;

import ey.com.personas.mspersonas.shared.enumeration.AccountTypes;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CreateAccountMessage implements Serializable {

    @Serial private static final long serialVersionUID = 1534511533326710612L;

    private AccountTypes accountType;
    private RegistrarPersonaRequest request;
}
