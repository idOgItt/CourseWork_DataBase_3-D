package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.ReviewDto;
import com.threed_model_market.project.exception_handler.exceptions.Model.ModelNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.Review.ReviewNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.model.Model;
import com.threed_model_market.project.model.Review;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.ModelRepository;
import com.threed_model_market.project.repository.ReviewRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ModelRepository modelRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public Review addReview(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Model model = modelRepository.findById(reviewDto.getModelId())
                .orElseThrow(() -> new ModelNotFoundException("Model not found"));

        Review review = new Review();
        review.setUser(user);
        review.setModel(model);
        review.setText(reviewDto.getText());
        review.setRating(reviewDto.getRating());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByModelId(Long modelId) {
        return reviewRepository.findByModelId(modelId);
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
        reviewRepository.delete(review);
    }
}
