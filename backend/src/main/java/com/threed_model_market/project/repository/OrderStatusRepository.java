package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    @SuppressWarnings("unused")
    boolean existsByName(String name);
}
