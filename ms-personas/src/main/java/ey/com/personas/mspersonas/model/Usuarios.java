package ey.com.personas.mspersonas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuarios implements Serializable {

    @Serial
    private static final long serialVersionUID = 3699378203011394067L;

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
    @JoinColumn(name = "fk_estado_usuario")
    private EstadoUsuario estado;

    @OneToOne
    @JoinColumn(name = "fk_tipo_usuario")
    private TipoUsuario tipo;
}
