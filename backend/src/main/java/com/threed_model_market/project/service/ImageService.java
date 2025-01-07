package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.ImageDto;
import com.threed_model_market.project.model.Image;

import java.util.Optional;

public interface ImageService {
    Image uploadImage(ImageDto imageDto);

    Optional<Image> getImageByModelIdAndFilename(Long modelId, String filename);

    Optional<Image> getImageByFilename(String filename);

    void deleteImage(Long id);

    Optional<Image> getImageById(Long id);
}
