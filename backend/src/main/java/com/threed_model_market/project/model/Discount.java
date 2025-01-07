package com.threed_model_market.project.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discounts_id_gen")
    @SequenceGenerator(name = "discounts_id_gen", sequenceName = "discounts_discountid_seq", allocationSize = 1)
    @Column(name = "discountid", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50, unique = true)
    private String code;

    @NotNull
    @Column(name = "discountamount", nullable = false)
    private BigDecimal discountAmount;

    @Size(max = 20)
    @NotNull
    @Column(name = "discounttype", nullable = false, length = 20)
    private String discountType;

    @NotNull
    @Column(name = "startdate", nullable = false)
    private OffsetDateTime startDate;

    @NotNull
    @Column(name = "enddate", nullable = false)
    private OffsetDateTime endDate;

    @Column(name = "usagelimit")
    private Integer usageLimit;

    @ColumnDefault("0")
    @Column(name = "timesused", nullable = false)
    private Integer timesUsed = 0;

    public enum DiscountType {
        FIXED, PERCENT
    }
}
