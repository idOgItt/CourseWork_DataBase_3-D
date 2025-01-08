package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VOrderDetailDto;
import com.threed_model_market.project.service.Views.VOrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v-order-details")
public class VOrderDetailController {

    private final VOrderDetailService vOrderDetailService;

    public VOrderDetailController(VOrderDetailService vOrderDetailService) {
        this.vOrderDetailService = vOrderDetailService;
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_DETAILS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<VOrderDetailDto>> getAllOrderDetails() {
        List<VOrderDetailDto> orderDetails = vOrderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_DETAILS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<VOrderDetailDto>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<VOrderDetailDto> orderDetails = vOrderDetailService.getOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_DETAILS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/{username}")
    public ResponseEntity<List<VOrderDetailDto>> getOrderDetailsByUser(@PathVariable String username) {
        List<VOrderDetailDto> orderDetails = vOrderDetailService.getOrderDetailsByUser(username);
        return ResponseEntity.ok(orderDetails);
    }

    @PreAuthorize("hasAuthority('VIEW_ORDER_DETAILS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<VOrderDetailDto>> getOrderDetailsByStatus(@PathVariable String status) {
        List<VOrderDetailDto> orderDetails = vOrderDetailService.getOrderDetailsByStatus(status);
        return ResponseEntity.ok(orderDetails);
    }
}
