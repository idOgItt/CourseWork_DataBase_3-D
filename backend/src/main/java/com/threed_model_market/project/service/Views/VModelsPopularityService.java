package com.threed_model_market.project.service.Views;

import com.threed_model_market.project.dto.Views.VModelsPopularityDto;

import java.util.List;

public interface VModelsPopularityService {
    List<VModelsPopularityDto> getTopSellingModels();
    List<VModelsPopularityDto> getMostOrderedModels();
}
