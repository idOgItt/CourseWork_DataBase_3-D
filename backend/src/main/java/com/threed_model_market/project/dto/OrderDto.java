package com.threed_model_market.project.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderDto {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @Size(max = 50, message = "Discount code must not exceed 50 characters")
    private String discountCode;

    private Instant orderDate;

    private BigDecimal totalAmount;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|PAID|SHIPPED|DELIVERED|CANCELLED", message = "Invalid status")
    private String status;
}
