package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.ModelDto;

import java.util.List;

public interface ModelService {
    ModelDto createModel(ModelDto modelDto);

    ModelDto updateModel(Long id, ModelDto modelDto);

    List<ModelDto> getAllModels();

    ModelDto getModelById(Long id);

    List<ModelDto> getModelsByUserId(Long userId);

    List<ModelDto> getModelsByCategoryId(Long categoryId);

    void deleteModel(Long id);
}
