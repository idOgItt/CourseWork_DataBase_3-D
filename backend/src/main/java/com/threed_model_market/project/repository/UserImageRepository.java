package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    Optional<UserImage> findByFilename(String filename);
    Optional<UserImage> findByUserId(Long userId);
}
