package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.ModelStatusDto;
import com.threed_model_market.project.model.ModelStatus;

import java.util.List;

public interface ModelStatusService {
    ModelStatus createModelStatus(ModelStatusDto modelStatusDto);
    List<ModelStatus> getAllModelStatuses();
    ModelStatus getModelStatusById(Long id);
    void removeModelStatus(Long id);
}
