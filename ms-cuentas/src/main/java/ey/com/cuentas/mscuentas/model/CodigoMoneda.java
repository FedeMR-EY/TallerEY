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
@Table(name = "codigo_moneda")
@Getter
@Setter
public class CodigoMoneda implements Serializable {

    @Serial
    private static final long serialVersionUID = -7837209169737143728L;

    @Id
    @Column(name = "cod_moneda")
    private Integer codMoneda;

    @Column(name = "pais")
    private String pais;

    @Column(name = "simbolo")
    private String simbolo;
}
