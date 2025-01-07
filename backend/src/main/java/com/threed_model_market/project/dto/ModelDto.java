package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ModelDto {
    private Long id;
    private Long userId;
    private Integer categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal rating;
    private Integer quantityAvailable;
    private String status;
    private List<ImageDto> images;
    private List<ReviewDto> reviews;
}
