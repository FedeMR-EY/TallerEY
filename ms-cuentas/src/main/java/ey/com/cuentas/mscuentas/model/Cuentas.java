package ey.com.cuentas.mscuentas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@ToString
public class Cuentas implements Serializable {
  @Serial private static final long serialVersionUID = 4939453752090296073L;

  @Id
  @Column(name = "numcue")
  private String numcuenta;

  @Column(name = "persnum")
  private Integer persnum;

  @OneToOne
  @JoinColumn(name = "divisa", referencedColumnName = "cod_moneda")
  private CodigoMoneda divisa;

  @OneToOne
  @JoinColumn(name = "estado", referencedColumnName = "id")
  private EstadoCuenta estado;

  @Column(name = "saldo")
  private BigDecimal saldo;
}
