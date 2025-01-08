package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.OrderStatusDto;
import com.threed_model_market.project.exception_handler.exceptions.OrderStatus.OrderStatusNotFoundException;
import com.threed_model_market.project.model.OrderStatus;
import com.threed_model_market.project.repository.OrderStatusRepository;
import com.threed_model_market.project.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus createOrderStatus(OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setName(orderStatusDto.getName());
        orderStatus.setDescription(orderStatusDto.getDescription());
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus getOrderStatusById(Long id) {
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new OrderStatusNotFoundException("Order status not found with ID: " + id));
    }

    @Override
    public void removeOrderStatus(Long id) {
        OrderStatus orderStatus = getOrderStatusById(id);
        orderStatusRepository.delete(orderStatus);
    }
}
