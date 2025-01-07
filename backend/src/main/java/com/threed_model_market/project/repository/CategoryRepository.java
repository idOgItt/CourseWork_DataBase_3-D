package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unused")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
