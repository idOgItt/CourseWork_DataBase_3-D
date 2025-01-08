package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.ModelDto;
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
    public Model createModel(ModelDto modelDto) {
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


        return modelRepository.save(model);
    }

    @Override
    public Model updateModel(Long id, ModelDto modelDto) {
        Model model = getModelById(id);

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

        return modelRepository.save(model);
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found with ID: " + id));
    }

    @Override
    public List<Model> getModelsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        return modelRepository.findByUserId(userId);
    }

    @Override
    public List<Model> getModelsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
        return modelRepository.findByCategoryId(categoryId);
    }

    @Override
    public void deleteModel(Long id) {
        Model model = getModelById(id);
        modelRepository.delete(model);
    }
}
