package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.OrderItemDto;
import com.threed_model_market.project.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemDto orderItemDto);

    List<OrderItem> getOrderItemsByOrderId(Long orderId);

    void deleteOrderItem(Long id);
}
