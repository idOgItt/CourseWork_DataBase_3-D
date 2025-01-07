package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.OrderDto;
import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.model.Discount;
import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.DiscountRepository;
import com.threed_model_market.project.repository.OrderRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, DiscountRepository discountRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Discount discount = null;
        if (orderDto.getDiscountCode() != null) {
            discount = discountRepository.findByCode(orderDto.getDiscountCode())
                    .orElseThrow(() -> new RuntimeException("Discount not found"));
        }

        Order order = new Order();
        order.setUser(user);
        order.setDiscount(discount);
        order.setStatus(orderDto.getStatus());

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
