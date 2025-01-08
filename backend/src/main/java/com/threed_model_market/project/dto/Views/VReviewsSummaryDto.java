package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class VReviewsSummaryDto {
    private Long id;
    private Long reviewId;
    private String reviewer;
    private String modelName;
    private String reviewText;
    private Long rating;
    private Instant reviewDate;

    public VReviewsSummaryDto(Long id, Long reviewId, String reviewer, String modelName,
                              String reviewText, Long rating, Instant reviewDate) {
        this.id = id;
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.modelName = modelName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }
}
