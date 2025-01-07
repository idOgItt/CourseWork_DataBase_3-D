package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.CategoryDto;
import com.threed_model_market.project.model.Category;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.CategoryService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public CategoryController(CategoryService categoryService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.categoryService = categoryService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('MANAGE_CATEGORIES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);

        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(category);
    }

    @PreAuthorize("hasAuthority('VIEW_CATEGORIES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasAuthority('VIEW_CATEGORIES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PreAuthorize("hasAuthority('MANAGE_CATEGORIES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> removeCategory(@PathVariable Integer categoryId, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);

        categoryService.removeCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
