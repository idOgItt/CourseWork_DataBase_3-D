package com.threed_model_market.project.repository.Views;

import com.threed_model_market.project.model.Views.VOrdersSummary;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VOrdersSummaryRepository extends JpaRepository<VOrdersSummary, Long> {

    @NotNull
    List<VOrdersSummary> findAll();

    VOrdersSummary findByOrderId(Integer orderId);

}
