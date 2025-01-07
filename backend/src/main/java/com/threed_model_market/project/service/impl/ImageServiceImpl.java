package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.model.Model;
import com.threed_model_market.project.dto.ImageDto;
import com.threed_model_market.project.exception_handler.exceptions.Image.ImageNotFoundException;
import com.threed_model_market.project.model.Image;
import com.threed_model_market.project.repository.ImageRepository;
import com.threed_model_market.project.repository.ModelRepository;
import com.threed_model_market.project.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ModelRepository modelRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ModelRepository modelRepository) {
        this.imageRepository = imageRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public Image uploadImage(ImageDto imageDto) {
        Model model = modelRepository.findById(imageDto.getModelId())
                .orElseThrow(() -> new ImageNotFoundException("Model not found with ID: " + imageDto.getModelId()));

        Image image = new Image();
        image.setFilename(imageDto.getFileName());
        image.setFiledata(imageDto.getFileData());
        image.setModel(model);

        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> getImageByModelIdAndFilename(Long modelId, String filename) {
        return imageRepository.findByModelIdAndFilename(modelId, filename);
    }

    @Override
    public Optional<Image> getImageByFilename(String filename) {
        return imageRepository.findByFilename(filename);
    }

    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image not found with ID: " + id));
        imageRepository.delete(image);
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }
}
