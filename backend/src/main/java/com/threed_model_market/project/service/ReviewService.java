package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.ReviewDto;
import com.threed_model_market.project.model.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(ReviewDto reviewDto);

    List<Review> getReviewsByModelId(Long modelId);

    List<Review> getReviewsByUserId(Long userId);

    void deleteReview(Long reviewId);
}
