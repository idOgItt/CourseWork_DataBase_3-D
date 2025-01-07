package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long orderId;
    private Long modelId;
    private Integer quantity;
}
