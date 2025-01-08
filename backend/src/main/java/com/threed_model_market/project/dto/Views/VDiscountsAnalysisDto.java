package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class VDiscountsAnalysisDto {
    private Long id;
    private String discountCode;
    private BigDecimal discountAmount;
    private String discountType;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Integer usageLimit;
    private Long ordersUsingDiscount;
}
