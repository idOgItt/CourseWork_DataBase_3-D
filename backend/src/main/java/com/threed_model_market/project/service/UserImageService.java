package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.UserImageDto;
import com.threed_model_market.project.model.UserImage;

import java.util.Optional;

public interface UserImageService {
    UserImage uploadUserImage(UserImageDto userImageDto);

    Optional<UserImage> getUserImageByUserId(Long userId);

    Optional<UserImage> getUserImageByFilename(String filename);

    void deleteUserImage(Long id);

    Optional<UserImage> getUserImageById(Long id);
}
