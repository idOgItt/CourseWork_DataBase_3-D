package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByUserId(Long userId);
    List<Model> findByCategoryId(Long category_id);
}
