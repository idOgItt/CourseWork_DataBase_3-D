package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;

@Repository
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void refresh(@NonNull Order order) {
        entityManager.refresh(order);
    }
}
