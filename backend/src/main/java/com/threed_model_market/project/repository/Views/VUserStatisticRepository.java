package com.threed_model_market.project.repository.Views;

import com.threed_model_market.project.model.Views.VUserStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VUserStatisticRepository extends JpaRepository<VUserStatistic, Long> {

    @SuppressWarnings("unused")
    List<VUserStatistic> findByUserId(Long userId);

    @SuppressWarnings("unused")
    List<VUserStatistic> findAllUserStatistics();
}
