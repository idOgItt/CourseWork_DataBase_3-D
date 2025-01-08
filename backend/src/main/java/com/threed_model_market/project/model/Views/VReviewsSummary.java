package com.threed_model_market.project.model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_reviews_summary")
public class VReviewsSummary {
    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "reviewid")
    private Long reviewId;

    @Size(max = 50)
    @Column(name = "\"Reviewer\"", length = 50)
    private String reviewer;

    @Size(max = 100)
    @Column(name = "\"ModelName\"", length = 100)
    private String modelName;

    @Size(max = 300)
    @Column(name = "\"ReviewText\"", length = 300)
    private String reviewText;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "\"ReviewDate\"")
    private Instant reviewDate;

}