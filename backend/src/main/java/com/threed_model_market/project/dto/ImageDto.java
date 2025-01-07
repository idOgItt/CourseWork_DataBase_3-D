package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageDto {

    private Long modelId;
    private String fileName;
    private byte[] fileData;

    public ImageDto(Long modelId, String fileName, byte[] fileData) {
        this.modelId = modelId;
        this.fileName = fileName;
        this.fileData = fileData;
    }
}
