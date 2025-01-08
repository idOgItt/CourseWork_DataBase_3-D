package com.threed_model_market.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "discounttypes")
public class DiscountType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discounttypes_id_gen")
    @SequenceGenerator(name = "discounttypes_id_gen", sequenceName = "discounttypes_discounttypeid_seq", allocationSize = 1)
    @Column(name = "discounttypeid", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;
}
