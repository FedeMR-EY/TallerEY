package ey.com.cuentas.mscuentas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
public class Cuentas implements Serializable {
    @Serial
    private static final long serialVersionUID = 4939453752090296073L;
    @Id
    @Column(name = "numcue")
    private String numcuenta;

    @Column(name = "persnum")
    private Integer persnum;

    @OneToOne
    @JoinColumn(name = "fk_codigomoneda")
    private CodigoMoneda divisa;

    @OneToOne
    @JoinColumn(name = "fk_estado")
    private EstadoCuenta estado;

    @Column(name = "saldo")
    private BigDecimal saldo;
}
