package com.threed_model_market.project.service.Views;

import com.threed_model_market.project.model.Views.VReviewsSummary;

import java.util.List;

public interface VReviewsSummaryService {
    List<VReviewsSummary> getAllReviews();

    VReviewsSummary getReviewById(Long id);
}
