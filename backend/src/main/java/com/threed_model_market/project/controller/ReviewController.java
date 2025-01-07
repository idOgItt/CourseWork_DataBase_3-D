package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.ReviewDto;
import com.threed_model_market.project.model.Review;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.ReviewService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public ReviewController(ReviewService reviewService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.reviewService = reviewService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('ADD_REVIEWS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto reviewDto, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, reviewDto.getUserId());
        Review review = reviewService.addReview(reviewDto);
        return ResponseEntity.ok(review);
    }

    @PreAuthorize("hasAuthority('VIEW_REVIEWS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<Review>> getReviewsByModelId(@PathVariable Long modelId) {
        return ResponseEntity.ok(reviewService.getReviewsByModelId(modelId));
    }

    @PreAuthorize("hasAuthority('VIEW_REVIEWS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }

    @PreAuthorize("hasAuthority('DELETE_REVIEWS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, jwtTokenProvider.getUserIdFromJWT(token));
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
