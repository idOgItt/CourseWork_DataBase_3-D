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
@Table(name = "models", schema = "public")
public class Model {
    @Id
    @ColumnDefault("nextval('models_modelid_seq'::regclass)")
    @Column(name = "modelid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 800)
    @Column(name = "description", length = 800)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")
    private Category categoryid;

    @ColumnDefault("0.0")
    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @NotNull
    @Column(name = "quantityavailable", nullable = false)
    private Integer quantityavailable;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "dateadded")
    private Instant dateadded;

}