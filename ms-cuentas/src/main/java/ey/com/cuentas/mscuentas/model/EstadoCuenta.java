package ey.com.cuentas.mscuentas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "estado_cuenta")
@Getter
@Setter
@ToString
public class EstadoCuenta implements Serializable {
  @Serial private static final long serialVersionUID = 2571243555297053212L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "detalle")
  private String detalle;
}
