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
public class Domicilio implements Serializable {

    private static final long serialVersionUID = -5774919253074325284L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String calle;
    private String altura;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
}
