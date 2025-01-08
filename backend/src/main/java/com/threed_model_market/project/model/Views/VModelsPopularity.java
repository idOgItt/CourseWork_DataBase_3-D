package com.threed_model_market.project.model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_models_popularity")
public class VModelsPopularity {
    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "modelid")
    private Integer modelId;

    @Size(max = 100)
    @Column(name = "\"ModelName\"", length = 100)
    private String modelName;

    @Size(max = 100)
    @Column(name = "\"Category\"", length = 100)
    private String category;

    @Column(name = "\"TotalSold\"")
    private Long totalSold;

    @Column(name = "\"TotalOrders\"")
    private Long totalOrders;

}