package com.threed_model_market.project;

import com.threed_model_market.project.model.User;
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
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @ColumnDefault("nextval('orders_orderid_seq'::regclass)")
    @Column(name = "orderid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "orderdate")
    private Instant orderdate;

    @NotNull
    @Column(name = "totalamount", nullable = false)
    private BigDecimal totalamount;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discountcode", referencedColumnName = "code")
    private Discount discountcode;

}