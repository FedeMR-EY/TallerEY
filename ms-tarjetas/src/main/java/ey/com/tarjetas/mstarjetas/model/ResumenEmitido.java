package ey.com.tarjetas.mstarjetas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "resumen_emitidos")
public class ResumenEmitido implements Serializable {

    @Serial
    private static final long serialVersionUID = 5748200324149063314L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resumenMongoID")
    private String resumemongoid;

    @Column(name = "total_pesos")
    private BigDecimal totalPesos;

    @Column(name = "total_Dolares")
    private BigDecimal totalDolares;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
}
