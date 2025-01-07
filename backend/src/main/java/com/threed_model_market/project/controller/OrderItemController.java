package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.OrderItemDto;
import com.threed_model_market.project.model.OrderItem;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.OrderItemService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public OrderItemController(OrderItemService orderItemService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.orderItemService = orderItemService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('MANAGE_ORDER_ITEMS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDto orderItemDto, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);

        OrderItem orderItem = orderItemService.createOrderItem(orderItemDto);
        return ResponseEntity.ok(orderItem);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_ITEMS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getOrderItemsByOrderId(orderId));
    }

    @PreAuthorize("hasAuthority('MANAGE_ORDER_ITEMS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, jwtTokenProvider.getUserIdFromJWT(token));
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
