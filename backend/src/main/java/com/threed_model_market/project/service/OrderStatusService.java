package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.OrderStatusDto;
import com.threed_model_market.project.model.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    OrderStatus createOrderStatus(OrderStatusDto orderStatusDto);
    List<OrderStatus> getAllOrderStatuses();
    OrderStatus getOrderStatusById(Long id);
    void removeOrderStatus(Long id);
}
