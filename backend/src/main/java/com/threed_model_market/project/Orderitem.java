package com.threed_model_market.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "orderitems", schema = "public")
public class Orderitem {
    @Id
    @ColumnDefault("nextval('orderitems_orderitemid_seq'::regclass)")
    @Column(name = "orderitemid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order orderid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelid")
    private Model modelid;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}