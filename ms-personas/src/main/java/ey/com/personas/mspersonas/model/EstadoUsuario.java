package ey.com.personas.mspersonas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "estado_usuario")
@ToString
public class EstadoUsuario implements Serializable {

  @Serial private static final long serialVersionUID = -5803892244986809751L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idestado_usuario")
  private Integer idestadoUsuario;

  @Column(name = "descripcion")
  private String descripcion;
}
