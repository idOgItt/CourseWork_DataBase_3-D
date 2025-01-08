package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.ModelStatusDto;
import com.threed_model_market.project.exception_handler.exceptions.ModelStatus.ModelStatusNotFoundException;
import com.threed_model_market.project.model.ModelStatus;
import com.threed_model_market.project.repository.ModelStatusRepository;
import com.threed_model_market.project.service.ModelStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelStatusServiceImpl implements ModelStatusService {

    private final ModelStatusRepository modelStatusRepository;

    public ModelStatusServiceImpl(ModelStatusRepository modelStatusRepository) {
        this.modelStatusRepository = modelStatusRepository;
    }

    @Override
    public ModelStatus createModelStatus(ModelStatusDto modelStatusDto) {
        ModelStatus modelStatus = new ModelStatus();
        modelStatus.setName(modelStatusDto.getName());
        modelStatus.setDescription(modelStatusDto.getDescription());
        return modelStatusRepository.save(modelStatus);
    }

    @Override
    public List<ModelStatus> getAllModelStatuses() {
        return modelStatusRepository.findAll();
    }

    @Override
    public ModelStatus getModelStatusById(Long id) {
        return modelStatusRepository.findById(id)
                .orElseThrow(() -> new ModelStatusNotFoundException("ModelStatus not found with ID: " + id));
    }

    @Override
    public void removeModelStatus(Long id) {
        ModelStatus modelStatus = modelStatusRepository.findById(id)
                .orElseThrow(() -> new ModelStatusNotFoundException("ModelStatus not found with ID: " + id));
        modelStatusRepository.delete(modelStatus);
    }
}
