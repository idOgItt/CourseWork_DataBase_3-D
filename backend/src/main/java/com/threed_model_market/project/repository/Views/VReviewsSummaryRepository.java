package com.threed_model_market.project.repository.Views;

import com.threed_model_market.project.model.Views.VReviewsSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VReviewsSummaryRepository extends JpaRepository<VReviewsSummary, Long> {
}
