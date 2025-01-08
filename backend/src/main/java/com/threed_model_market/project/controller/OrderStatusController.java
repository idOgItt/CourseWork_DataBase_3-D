package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.OrderStatusDto;
import com.threed_model_market.project.model.OrderStatus;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.OrderStatusService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public OrderStatusController(OrderStatusService orderStatusService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.orderStatusService = orderStatusService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('MANAGE_ORDER_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<OrderStatus> createOrderStatus(@RequestBody OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = orderStatusService.createOrderStatus(orderStatusDto);
        return ResponseEntity.ok(orderStatus);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<OrderStatus>> getAllOrderStatuses(@RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        return ResponseEntity.ok(orderStatusService.getAllOrderStatuses());
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderStatus> getOrderStatusById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        return ResponseEntity.ok(orderStatusService.getOrderStatusById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_ORDER_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeOrderStatus(@PathVariable Long id) {
        orderStatusService.removeOrderStatus(id);
        return ResponseEntity.noContent().build();
    }
}
