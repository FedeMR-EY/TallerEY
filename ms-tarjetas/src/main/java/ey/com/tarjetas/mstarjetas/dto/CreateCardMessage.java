package ey.com.tarjetas.mstarjetas.dto;

import ey.com.tarjetas.mstarjetas.shared.enumeration.AccountTypes;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record CreateCardMessage(AccountTypes accountTypes, UUID uuid) implements Serializable {
  @Serial private static final long serialVersionUID = -6584436056539593504L;
}
