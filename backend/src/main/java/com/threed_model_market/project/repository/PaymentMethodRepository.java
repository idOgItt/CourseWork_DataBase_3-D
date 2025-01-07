package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    @SuppressWarnings("unused")
    Optional<PaymentMethod> findByName(String name);
}
