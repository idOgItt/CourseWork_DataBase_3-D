package com.threed_model_market.project.repository.Views;

import com.threed_model_market.project.model.Views.VLogsSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VLogsSummaryRepository extends JpaRepository<VLogsSummary, Long> {
    List<VLogsSummary> findByAction(String action);
    List<VLogsSummary> findByUsername(String username);
    List<VLogsSummary> findByTimestampBetween(java.time.Instant timestamp, java.time.Instant timestamp2);
}
