package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {
    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod;
}
