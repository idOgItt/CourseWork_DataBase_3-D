package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long userId;
    private Long modelId;
    private String text;
    private Integer rating;
}
