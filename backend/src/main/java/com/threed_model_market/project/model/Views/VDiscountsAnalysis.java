package com.threed_model_market.project.model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_discounts_analysis")
public class VDiscountsAnalysis {
    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Size(max = 50)
    @Column(name = "\"DiscountCode\"", length = 50)
    private String discountCode;

    @Column(name = "discountamount")
    private BigDecimal discountAmount;

    @Size(max = 20)
    @Column(name = "\"DiscountType\"", length = 20)
    private String discountType;

    @Column(name = "startdate")
    private OffsetDateTime startDate;

    @Column(name = "enddate")
    private OffsetDateTime endDate;

    @Column(name = "usagelimit")
    private Integer usageLimit;

    @Column(name = "\"OrdersUsingDiscount\"")
    private Long ordersUsingDiscount;
}
