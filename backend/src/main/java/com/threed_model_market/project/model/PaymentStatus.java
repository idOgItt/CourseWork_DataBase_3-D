package com.threed_model_market.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "paymentstatuses")
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentstatuses_id_gen")
    @SequenceGenerator(name = "paymentstatuses_id_gen", sequenceName = "paymentstatuses_statusid_seq", allocationSize = 1)
    @Column(name = "statusid", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'PENDING'")
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "paymentStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();
}