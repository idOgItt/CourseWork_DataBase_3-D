package com.threed_model_market.project.service.Views;

import com.threed_model_market.project.dto.Views.VUserStatisticDto;

import java.util.List;

public interface VUserStatisticService {
    VUserStatisticDto getUserStatisticById(Long id);
    List<VUserStatisticDto> getAllUserStatistics();
}
