package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {

    @EntityGraph(attributePaths = {"user", "discount"})
    @NonNull
    Optional<Order> findById(@NonNull Long id);

    @EntityGraph(attributePaths = {"user", "discount"})
    @NonNull
    List<Order> findByUserId(@NonNull Long userId);

    @SuppressWarnings("unused")
    List<Order> findByStatus_Name(String status);
}
