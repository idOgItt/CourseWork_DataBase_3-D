package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByModelId(Long modelId);
    List<Review> findByUserId(Long userId);
}
