package ey.com.tarjetas.mstarjetas.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record CreateCardMessageResponse(UUID uuid, Boolean created) implements Serializable {
    @Serial
    private static final long serialVersionUID = 5009991502438745711L;
}
