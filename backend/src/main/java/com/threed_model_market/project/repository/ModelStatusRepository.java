package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelStatusRepository extends JpaRepository<ModelStatus, Long> {
    @SuppressWarnings("unused")
    Optional<ModelStatus> findByName(String name);
}
