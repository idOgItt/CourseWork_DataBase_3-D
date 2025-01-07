package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByModelIdAndFilename(Long modelId, String filename);
    Optional<Image> findByFilename(String filename);
}
