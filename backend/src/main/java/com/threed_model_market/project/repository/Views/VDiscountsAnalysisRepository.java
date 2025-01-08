package com.threed_model_market.project.repository.Views;

import com.threed_model_market.project.model.Views.VDiscountsAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDiscountsAnalysisRepository extends JpaRepository<VDiscountsAnalysis, Long> {
}
