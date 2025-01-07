package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.OrderItemDto;
import com.threed_model_market.project.exception_handler.exceptions.Model.ModelNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.model.Model;
import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.model.OrderItem;
import com.threed_model_market.project.repository.ModelRepository;
import com.threed_model_market.project.repository.OrderItemRepository;
import com.threed_model_market.project.repository.OrderRepository;
import com.threed_model_market.project.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ModelRepository modelRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ModelRepository modelRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItemDto orderItemDto) {
        Order order = orderRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderItemDto.getOrderId()));

        Model model = modelRepository.findById(orderItemDto.getModelId())
                .orElseThrow(() -> new ModelNotFoundException("Model not found with ID: " + orderItemDto.getModelId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setModel(model);
        orderItem.setQuantity(orderItemDto.getQuantity());

        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
