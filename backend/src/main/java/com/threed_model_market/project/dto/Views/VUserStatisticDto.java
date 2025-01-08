package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VUserStatisticDto {

    private Long id;
    private Long userId;
    private String username;
    private String email;
    private Long totalOrders;
    private BigDecimal totalSpent;
    private Long totalReviews;


    public VUserStatisticDto(Long id, Long userId, String username, String email,
                             Long totalOrders, BigDecimal totalSpent, Long totalReviews) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.totalOrders = totalOrders;
        this.totalSpent = totalSpent;
        this.totalReviews = totalReviews;
    }
}
