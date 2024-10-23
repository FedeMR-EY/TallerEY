package ey.com.tarjetas.mstarjetas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "estado_tarjeta")
public class EstadoTarjeta implements Serializable {

  @Serial private static final long serialVersionUID = 5748200324149063314L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "detalle")
  private String detalle;
}
