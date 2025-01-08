package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.dto.Views.VModelsPopularityDto;
import com.threed_model_market.project.model.Views.VModelsPopularity;
import com.threed_model_market.project.repository.Views.VModelsPopularityRepository;
import com.threed_model_market.project.service.Views.VModelsPopularityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VModelsPopularityServiceImpl implements VModelsPopularityService {

    private final VModelsPopularityRepository vModelsPopularityRepository;

    public VModelsPopularityServiceImpl(VModelsPopularityRepository vModelsPopularityRepository) {
        this.vModelsPopularityRepository = vModelsPopularityRepository;
    }

    @Override
    public List<VModelsPopularityDto> getTopSellingModels() {
        return vModelsPopularityRepository.findAll().stream()
                .map(this::mapToDto)
                .sorted((a, b) -> b.getTotalSold().compareTo(a.getTotalSold()))
                .collect(Collectors.toList());
    }

    @Override
    public List<VModelsPopularityDto> getMostOrderedModels() {
        return vModelsPopularityRepository.findAll().stream()
                .map(this::mapToDto)
                .sorted((a, b) -> b.getTotalOrders().compareTo(a.getTotalOrders()))
                .collect(Collectors.toList());
    }

    private VModelsPopularityDto mapToDto(VModelsPopularity entity) {
        VModelsPopularityDto dto = new VModelsPopularityDto();
        dto.setModelId(Long.valueOf(entity.getModelId()));
        dto.setModelName(entity.getModelName());
        dto.setCategory(entity.getCategory());
        dto.setTotalSold(entity.getTotalSold());
        dto.setTotalOrders(entity.getTotalOrders());
        return dto;
    }
}
