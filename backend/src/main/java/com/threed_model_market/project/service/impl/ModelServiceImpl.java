package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.ModelDto;
import com.threed_model_market.project.dto.ImageDto;
import com.threed_model_market.project.dto.ReviewDto;
import com.threed_model_market.project.exception_handler.exceptions.Category.CategoryNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.Model.ModelNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.ModelStatus.ModelStatusNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.model.*;
import com.threed_model_market.project.repository.CategoryRepository;
import com.threed_model_market.project.repository.ModelRepository;
import com.threed_model_market.project.repository.ModelStatusRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelStatusRepository modelStatusRepository;

    public ModelServiceImpl(ModelRepository modelRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelStatusRepository modelStatusRepository) {
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelStatusRepository = modelStatusRepository;
    }

    @Override
    public ModelDto createModel(ModelDto modelDto) {
        User user = userRepository.findById(modelDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + modelDto.getUserId()));

        Category category = categoryRepository.findById(modelDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + modelDto.getCategoryId()));

        ModelStatus status = modelStatusRepository.findByName(modelDto.getStatusName())
                .orElseThrow(() -> new ModelStatusNotFoundException("Model status not found: " + modelDto.getStatusName()));

        Model model = new Model();
        model.setName(modelDto.getName());
        model.setDescription(modelDto.getDescription());
        model.setPrice(modelDto.getPrice());
        model.setCategory(category);
        model.setUser(user);
        model.setQuantityAvailable(modelDto.getQuantityAvailable());
        model.setStatus(status);

        return mapToDto(modelRepository.save(model));
    }

    @Override
    public ModelDto updateModel(Long id, ModelDto modelDto) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found with ID: " + id));

        Category category = categoryRepository.findById(modelDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + modelDto.getCategoryId()));

        ModelStatus status = modelStatusRepository.findByName(modelDto.getStatusName())
                .orElseThrow(() -> new ModelStatusNotFoundException("Model status not found: " + modelDto.getStatusName()));

        model.setCategory(category);
        model.setStatus(status);
        model.setName(modelDto.getName());
        model.setDescription(modelDto.getDescription());
        model.setPrice(modelDto.getPrice());
        model.setQuantityAvailable(modelDto.getQuantityAvailable());

        return mapToDto(modelRepository.save(model));
    }

    @Override
    public List<ModelDto> getAllModels() {
        List<Model> models = modelRepository.findAll();
        return models.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ModelDto getModelById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found with ID: " + id));
        return mapToDto(model);
    }

    @Override
    public List<ModelDto> getModelsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        return modelRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getModelsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
        return modelRepository.findByCategoryId(categoryId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteModel(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found with ID: " + id));
        modelRepository.delete(model);
    }

    private ModelDto mapToDto(Model model) {
        ModelDto dto = new ModelDto();
        dto.setId(model.getId());
        dto.setUserId(model.getUser().getId());
        dto.setCategoryId(Math.toIntExact(model.getCategory().getId()));
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setPrice(model.getPrice());
        dto.setRating(model.getRating());
        dto.setQuantityAvailable(model.getQuantityAvailable());
        dto.setStatusName(model.getStatus().getName());
        dto.setImages(model.getImages().stream()
                .map(image -> new ImageDto(
                        image.getModel().getId(),
                        image.getFilename(),
                        image.getFiledata()))
                .collect(Collectors.toList()));
        dto.setReviews(model.getReviews().stream()
                .map(review -> {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setId(review.getId());
                    reviewDto.setText(review.getText());
                    reviewDto.setRating(review.getRating());
                    return reviewDto;
                })
                .collect(Collectors.toList()));
        return dto;
    }
}
