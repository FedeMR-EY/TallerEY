package ey.com.personas.mspersonas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 300852996982844891L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String dni;
    private Domicilio domicilio;
    private EstadoUsuario estadoUsuario;
}
