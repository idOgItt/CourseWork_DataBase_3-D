package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.ModelDto;
import com.threed_model_market.project.model.Model;

import java.util.List;

public interface ModelService {
    Model createModel(ModelDto modelDto);

    Model getModelById(Long id);

    List<Model> getModelsByUserId(Long userId);

    List<Model> getModelsByCategoryId(Long categoryId);

    void deleteModel(Long id);
}
