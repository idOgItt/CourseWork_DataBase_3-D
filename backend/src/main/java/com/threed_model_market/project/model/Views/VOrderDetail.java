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

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_order_details")
public class VOrderDetail {
    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "orderid")
    private Long orderId;

    @Size(max = 50)
    @Column(name = "\"User\"", length = 50)
    private String user;

    @Column(name = "orderdate")
    private Instant orderDate;

    @Size(max = 20)
    @Column(name = "\"Status\"", length = 20)
    private String status;

    @Size(max = 100)
    @Column(name = "\"ModelName\"", length = 100)
    private String modelName;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "\"LineTotal\"")
    private BigDecimal lineTotal;

}