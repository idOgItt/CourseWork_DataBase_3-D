package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.OrderDto;
import com.threed_model_market.project.model.Order;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.OrderService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final AccessValidator accessValidator;
    private final JwtTokenProvider jwtTokenProvider;

    public OrderController(OrderService orderService, AccessValidator accessValidator, JwtTokenProvider jwtTokenProvider) {
        this.orderService = orderService;
        this.accessValidator = accessValidator;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PreAuthorize("hasAuthority('CREATE_ORDERS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, orderDto.getUserId());
        Order order = orderService.createOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Order order = orderService.getOrderById(id);
        accessValidator.validateUserAccess(token, order.getUser().getId());
        return ResponseEntity.ok(order);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, userId);
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @PreAuthorize("hasAuthority('DELETE_ORDERS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Order order = orderService.getOrderById(id);
        accessValidator.validateUserAccess(token, order.getUser().getId());
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('VIEW_ORDERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasAuthority('UPDATE_ORDERS') or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto, @RequestHeader("Authorization") String token) {
        Order order = orderService.getOrderById(id);
        accessValidator.validateUserAccess(token, order.getUser().getId());
        return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
    }
}
