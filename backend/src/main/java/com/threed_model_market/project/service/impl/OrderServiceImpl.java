package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.OrderDto;
import com.threed_model_market.project.exception_handler.exceptions.Discount.DiscountNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.Order.OrderNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.OrderStatus.OrderStatusNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.model.Discount;
import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.model.OrderStatus;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.DiscountRepository;
import com.threed_model_market.project.repository.OrderRepository;
import com.threed_model_market.project.repository.OrderStatusRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final OrderStatusRepository orderStatusRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, DiscountRepository discountRepository, OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Discount discount = null;
        if (orderDto.getDiscountCode() != null) {
            discount = discountRepository.findByCode(orderDto.getDiscountCode())
                    .orElseThrow(() -> new DiscountNotFoundException("Discount not found"));
        }

        OrderStatus status = orderStatusRepository.findByName(orderDto.getStatus())
                .orElseThrow(() -> new OrderStatusNotFoundException("Order status not found"));

        Order order = new Order();
        order.setUser(user);
        order.setDiscount(discount);
        order.setStatus(status);
        order.setOrderDate(orderDto.getOrderDate() != null ? orderDto.getOrderDate() : Instant.now());

        orderRepository.save(order);
        orderRepository.refresh(order);

        return order;
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

    @Override
    public Order updateOrder(Long id, OrderDto orderDto) {
        Order order = getOrderById(id);

        order.setStatus(orderStatusRepository.findByName(orderDto.getStatus())
                .orElseThrow(() -> new OrderNotFoundException("Order status not found: " + orderDto.getStatus())));
        order.setDiscount(discountRepository.findByCode(orderDto.getDiscountCode())
                .orElseThrow(() -> new DiscountNotFoundException("Discount not found: " + orderDto.getDiscountCode())));

        orderRepository.save(order);

        orderRepository.refresh(order);

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
