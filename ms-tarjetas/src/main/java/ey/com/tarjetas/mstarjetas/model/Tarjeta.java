package ey.com.tarjetas.mstarjetas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {

    @Serial
    private static final long serialVersionUID = -7844128770600628905L;

    @Id
    @Column(name = "numtarj")
    private String numtarj;

    @Column(name = "numcue")
    private Integer numcuenta;

    @Column(name = "f_vencimiento")
    private String fVencimiento;

    @Column(name = "pin")
    private Integer pin;

    @OneToOne
    @JoinColumn(name = "fk_estado")
    private EstadoTarjeta estado;

    @Column(name = "f_emision")
    private String fEmision;

    @Column(name = "tipo")
    private String tipo;
}
