package ey.com.cuentas.mscuentas.dto;

import ey.com.cuentas.mscuentas.shared.enumeration.AccountTypes;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record CreateAccountMessage(AccountTypes accountType, RegistrarPersonaRequest request, UUID uuid, Integer personNumber)
    implements Serializable {
  @Serial private static final long serialVersionUID = 1534511533326710612L;
}
