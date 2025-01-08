package com.threed_model_market.project.service.Views;

import com.threed_model_market.project.dto.Views.VOrdersSummaryDto;

import java.util.List;

public interface VOrdersSummaryService {
    List<VOrdersSummaryDto> getAllOrdersSummary();

    VOrdersSummaryDto getOrderSummaryByOrderId(Integer orderId);
}
