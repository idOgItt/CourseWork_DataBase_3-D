package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.CategoryDto;
import com.threed_model_market.project.exception_handler.exceptions.Category.CategoryNotFoundException;
import com.threed_model_market.project.model.Category;
import com.threed_model_market.project.repository.CategoryRepository;
import com.threed_model_market.project.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public void removeCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
