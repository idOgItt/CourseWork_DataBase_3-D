package com.threed_model_market.project.model;

import com.threed_model_market.project.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_id_gen")
    @SequenceGenerator(name = "payments_id_gen", sequenceName = "payments_paymentid_seq", allocationSize = 1)
    @Column(name = "paymentid", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;

    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Size(max = 50)
    @NotNull
    @Column(name = "paymentmethod", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "paymentdate", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant paymentDate;
}
