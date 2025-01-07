package com.threed_model_market.project.service;

import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    void deleteOrder(Long id);
}
