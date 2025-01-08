package com.threed_model_market.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "orderstatuses")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderstatuses_id_gen")
    @SequenceGenerator(name = "orderstatuses_id_gen", sequenceName = "orderstatuses_statusid_seq", allocationSize = 1)
    @Column(name = "statusid", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'PENDING'")
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;
}
