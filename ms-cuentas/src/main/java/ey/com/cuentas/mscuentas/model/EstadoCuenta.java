package ey.com.cuentas.mscuentas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "estado_cuenta")
@Getter
@Setter
public class EstadoCuenta implements Serializable {
    @Serial
    private static final long serialVersionUID = 2571243555297053212L;

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "detalle")
    private String detalle;
}
