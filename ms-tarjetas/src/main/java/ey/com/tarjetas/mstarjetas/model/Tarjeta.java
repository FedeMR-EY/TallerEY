package ey.com.tarjetas.mstarjetas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {

  @Serial private static final long serialVersionUID = -7844128770600628905L;

  @Id
  @Column(name = "numtarj")
  private String numtarj;

  @Column(name = "numcue")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer numcuenta;

  @Column(name = "f_vencimiento")
  private String fVencimiento;

  @Column(name = "pin")
  private Integer pin;

  @OneToOne
  @JoinColumn(name = "estado", referencedColumnName = "id")
  private EstadoTarjeta estado;

  @Column(name = "f_emision")
  private String fEmision;

  @Column(name = "tipo")
  private String tipo;
}
