package com.threed_model_market.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderitems_id_gen")
    @SequenceGenerator(name = "orderitems_id_gen", sequenceName = "orderitems_orderitemid_seq", allocationSize = 1)
    @Column(name = "orderitemid", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modelid", nullable = false)
    private Model model;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
