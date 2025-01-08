package com.threed_model_market.project.service.Views;

import com.threed_model_market.project.dto.Views.VOrderDetailDto;

import java.util.List;

public interface VOrderDetailService {
    List<VOrderDetailDto> getAllOrderDetails();
    List<VOrderDetailDto> getOrderDetailsByOrderId(Long orderId);
    List<VOrderDetailDto> getOrderDetailsByUser(String username);
    List<VOrderDetailDto> getOrderDetailsByStatus(String status);
}
