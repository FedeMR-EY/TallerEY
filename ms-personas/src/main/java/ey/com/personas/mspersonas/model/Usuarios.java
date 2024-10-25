package ey.com.personas.mspersonas.model;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
@ToString
public class Usuarios implements Serializable {

  @Serial private static final long serialVersionUID = 3699378203011394067L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "persnum")
  private Integer persnum;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "dni")
  private String dni;

  @OneToOne
  @JoinColumn(name = "estado", referencedColumnName = "idestado_usuario")
  private EstadoUsuario estado;

  @OneToOne
  @JoinColumn(name = "tipo", referencedColumnName = "idtipo_usuario")
  private TipoUsuario tipo;
}
