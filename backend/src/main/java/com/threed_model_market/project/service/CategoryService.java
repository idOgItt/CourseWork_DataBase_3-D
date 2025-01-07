package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.CategoryDto;
import com.threed_model_market.project.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
    Category getCategoryById(Integer categoryId);
    void removeCategory(Integer categoryId);
}
