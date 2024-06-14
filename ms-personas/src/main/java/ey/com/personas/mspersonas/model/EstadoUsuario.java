package ey.com.personas.mspersonas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "estado_usuario")
public class EstadoUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = -5803892244986809751L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado_usuario")
    private Integer idestadoUsuario;

    @Column(name = "descripcion")
    private String descripcion;
}
