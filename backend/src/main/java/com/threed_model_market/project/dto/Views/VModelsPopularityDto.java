package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VModelsPopularityDto {
    private Long modelId;
    private String modelName;
    private String category;
    private Long totalSold;
    private Long totalOrders;
}
