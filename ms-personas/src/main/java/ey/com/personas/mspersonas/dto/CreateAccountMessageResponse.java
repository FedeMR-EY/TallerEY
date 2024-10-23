package ey.com.personas.mspersonas.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record CreateAccountMessageResponse(UUID uuid, Boolean created, List<String> nroCuenta)
    implements Serializable {
  @Serial private static final long serialVersionUID = 5533389265493543201L;
}
