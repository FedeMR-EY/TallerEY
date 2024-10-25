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
@Table(name = "tipo_usuario")
@ToString
public class TipoUsuario implements Serializable {

  @Serial private static final long serialVersionUID = 300852996982844891L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idtipo_usuario")
  private Integer idtipoUsuario;

  @Column(name = "descripcion")
  private String descripcion;
}
