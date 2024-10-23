package ey.com.personas.mspersonas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "domicilio")
public class Domicilio implements Serializable {

  @Serial private static final long serialVersionUID = -5774919253074325284L;

  @Id
  @Column(name = "iddomicilio")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer iddomicilio;

  @Column(name = "persnum")
  private Integer persnum;

  @Column(name = "calle")
  private String calle;

  @Column(name = "numero")
  private String numero;

  @Column(name = "provincia")
  private String provincia;

  @Column(name = "localidad")
  private String localidad;
}
