package ey.com.personas.mspersonas.dto;

import ey.com.personas.mspersonas.shared.enumeration.AccountTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record CreateCardMessage(AccountTypes accountTypes, UUID uuid) implements Serializable {
    @Serial private static final long serialVersionUID = -6584436056539593504L;
}