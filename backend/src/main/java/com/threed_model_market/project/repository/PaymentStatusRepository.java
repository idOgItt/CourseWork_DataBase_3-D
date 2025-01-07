package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
    @SuppressWarnings("unused")
    Optional<PaymentStatus> findByName(String name);
}
