package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.ModelDto;
import com.threed_model_market.project.exception_handler.exceptions.Model.ModelNotFoundException;
import com.threed_model_market.project.model.Category;
import com.threed_model_market.project.model.Model;
import com.threed_model_market.project.model.Review;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.CategoryRepository;
import com.threed_model_market.project.repository.ModelRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ModelServiceImpl(ModelRepository modelRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Model createModel(ModelDto modelDto) {
        User user = userRepository.findById(modelDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Category category = categoryRepository.findById(modelDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Model model = new Model();
        model.setName(modelDto.getName());
        model.setDescription(modelDto.getDescription());
        model.setPrice(modelDto.getPrice());
        model.setCategory(category);
        model.setUser(user);
        model.setQuantityAvailable(modelDto.getQuantityAvailable());
        model.setStatus(modelDto.getStatus());

        List<Review> reviews = modelDto.getReviews().stream().map(reviewDto -> {
            Review review = new Review();
            review.setText(reviewDto.getText());
            review.setRating(reviewDto.getRating());
            review.setModel(model);
            review.setUser(userRepository.findById(reviewDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found")));
            return review;
        }).toList();
        model.setReviews(reviews);

        return modelRepository.save(model);
    }


    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found"));
    }

    @Override
    public List<Model> getModelsByUserId(Long userId) {
        return modelRepository.findByUserId(userId);
    }

    @Override
    public List<Model> getModelsByCategoryId(Long categoryId) {
        return modelRepository.findByCategoryId(categoryId);
    }

    @Override
    public void deleteModel(Long id) {
        if (!modelRepository.existsById(id)) {
            throw new ModelNotFoundException("Model not found");
        }
        modelRepository.deleteById(id);
    }
}
