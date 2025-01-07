package com.threed_model_market.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "discounts", schema = "public")
public class Discount {
    @Id
    @ColumnDefault("nextval('discounts_discountid_seq'::regclass)")
    @Column(name = "discountid", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @NotNull
    @Column(name = "discountamount", nullable = false)
    private BigDecimal discountamount;

    @Size(max = 20)
    @NotNull
    @Column(name = "discounttype", nullable = false, length = 20)
    private String discounttype;

    @NotNull
    @Column(name = "startdate", nullable = false)
    private OffsetDateTime startdate;

    @NotNull
    @Column(name = "enddate", nullable = false)
    private OffsetDateTime enddate;

    @Column(name = "usagelimit")
    private Integer usagelimit;

    @ColumnDefault("0")
    @Column(name = "timesused")
    private Integer timesused;

}