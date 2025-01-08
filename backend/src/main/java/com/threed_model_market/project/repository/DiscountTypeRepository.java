package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {
    Optional<DiscountType> findByName(String name);
}
