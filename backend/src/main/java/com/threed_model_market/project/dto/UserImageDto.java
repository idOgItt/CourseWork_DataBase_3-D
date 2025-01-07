package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserImageDto {
    private Long userId;
    private String fileName;
    private byte[] fileData;
}
