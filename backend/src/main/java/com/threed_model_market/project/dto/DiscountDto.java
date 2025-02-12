package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DiscountDto {
    private String code;
    private BigDecimal discountAmount;
    private String discountTypeName;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Integer usageLimit;
}
