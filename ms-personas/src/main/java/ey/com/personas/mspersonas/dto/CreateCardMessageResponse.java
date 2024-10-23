package ey.com.personas.mspersonas.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record CreateCardMessageResponse(UUID uuid, Boolean created) implements Serializable {
  @Serial private static final long serialVersionUID = 2942126381592421773L;
}
