package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @SuppressWarnings("unused")
    Category findByName(String name);

    boolean existsById(Long categoryId);
}
