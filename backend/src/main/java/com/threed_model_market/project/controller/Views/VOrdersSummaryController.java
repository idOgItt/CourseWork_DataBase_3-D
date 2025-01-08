package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VOrdersSummaryDto;
import com.threed_model_market.project.service.Views.VOrdersSummaryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders-summary")
public class VOrdersSummaryController {

    private final VOrdersSummaryService vOrdersSummaryService;

    public VOrdersSummaryController(VOrdersSummaryService vOrdersSummaryService) {
        this.vOrdersSummaryService = vOrdersSummaryService;
    }

    @PreAuthorize("hasAuthority('VIEW_ORDERS_SUMMARY') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public List<VOrdersSummaryDto> getAllOrdersSummary() {
        return vOrdersSummaryService.getAllOrdersSummary();
    }

    @PreAuthorize("hasAuthority('VIEW_ORDERS_SUMMARY') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{orderId}")
    public VOrdersSummaryDto getOrderSummaryByOrderId(@PathVariable Integer orderId) {
        return vOrdersSummaryService.getOrderSummaryByOrderId(orderId);
    }
}
