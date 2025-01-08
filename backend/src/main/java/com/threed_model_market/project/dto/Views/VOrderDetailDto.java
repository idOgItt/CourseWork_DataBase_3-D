package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class VOrderDetailDto {
    private Long id;
    private Long orderId;
    private String user;
    private Instant orderDate;
    private String status;
    private String modelName;
    private Long quantity;
    private BigDecimal lineTotal;
}
