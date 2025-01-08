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
import java.time.Instant;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_orders_summary")
public class VOrdersSummary {
    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "orderid")
    private Integer orderId;

    @Size(max = 50)
    @Column(name = "\"User\"", length = 50)
    private String user;

    @Size(max = 20)
    @Column(name = "\"Status\"", length = 20)
    private String status;

    @Column(name = "totalamount")
    private BigDecimal totalAmount;

    @Size(max = 50)
    @Column(name = "discountcode", length = 50)
    private String discountCode;

    @Column(name = "\"DiscountDetails\"", length = Integer.MAX_VALUE)
    private String discountDetails;

    @Column(name = "orderdate")
    private Instant orderDate;
}
