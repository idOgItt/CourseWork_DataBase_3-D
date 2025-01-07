package com.threed_model_market.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payments", schema = "public")
public class Payment {
    @Id
    @ColumnDefault("nextval('payments_paymentid_seq'::regclass)")
    @Column(name = "paymentid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order orderid;

    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Size(max = 50)
    @NotNull
    @Column(name = "paymentmethod", nullable = false, length = 50)
    private String paymentmethod;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "paymentdate")
    private Instant paymentdate;

}