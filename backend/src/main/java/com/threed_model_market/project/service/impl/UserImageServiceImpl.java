package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.UserImageDto;
import com.threed_model_market.project.exception_handler.exceptions.UserImage.UserImageNotFoundException;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.model.UserImage;
import com.threed_model_market.project.repository.UserImageRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.UserImageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImageServiceImpl implements UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;

    public UserImageServiceImpl(UserImageRepository userImageRepository, UserRepository userRepository) {
        this.userImageRepository = userImageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserImage uploadUserImage(UserImageDto userImageDto) {
        User user = userRepository.findById(userImageDto.getUserId())
                .orElseThrow(() -> new UserImageNotFoundException("User not found"));

        UserImage userImage = new UserImage();
        userImage.setUser(user);
        userImage.setFilename(userImageDto.getFileName());
        userImage.setFiledata(userImageDto.getFileData());

        return userImageRepository.save(userImage);
    }

    @Override
    public Optional<UserImage> getUserImageByUserId(Long userId) {
        return userImageRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserImage> getUserImageByFilename(String filename) {
        return userImageRepository.findByFilename(filename);
    }

    @Override
    public void deleteUserImage(Long id) {
        UserImage userImage = userImageRepository.findById(id)
                .orElseThrow(() -> new UserImageNotFoundException("User image not found"));
        userImageRepository.delete(userImage);
    }

    @Override
    public Optional<UserImage> getUserImageById(Long id) {
        return userImageRepository.findById(id);
    }
}
