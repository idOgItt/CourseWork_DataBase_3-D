package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Order;
import org.springframework.lang.NonNull;

public interface CustomOrderRepository {
    void refresh(@NonNull Order order);
}
