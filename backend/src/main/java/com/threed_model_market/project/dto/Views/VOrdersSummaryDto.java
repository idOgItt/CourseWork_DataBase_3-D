package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class VOrdersSummaryDto {

    private Long id;
    private Integer orderId;
    private String user;
    private String status;
    private BigDecimal totalAmount;
    private String discountCode;
    private String discountDetails;
    private Instant orderDate;

    public VOrdersSummaryDto(Long id, Integer orderId, String user, String status, BigDecimal totalAmount, String discountCode, String discountDetails, Instant orderDate) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.status = status;
        this.totalAmount = totalAmount;
        this.discountCode = discountCode;
        this.discountDetails = discountDetails;
        this.orderDate = orderDate;
    }
}
