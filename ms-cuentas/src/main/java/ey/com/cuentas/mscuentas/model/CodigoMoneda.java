package ey.com.cuentas.mscuentas.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "codigo_moneda")
@Getter
@Setter
public class CodigoMoneda implements Serializable {

  @Serial private static final long serialVersionUID = -7837209169737143728L;

  @Id
  @Column(name = "cod_moneda")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer codMoneda;

  @Column(name = "pais")
  private String pais;

  @Column(name = "simbolo")
  private String simbolo;
}
