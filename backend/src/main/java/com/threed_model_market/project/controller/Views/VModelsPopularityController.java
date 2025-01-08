package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VModelsPopularityDto;
import com.threed_model_market.project.service.Views.VModelsPopularityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/models-popularity")
public class VModelsPopularityController {

    private final VModelsPopularityService vModelsPopularityService;

    public VModelsPopularityController(VModelsPopularityService vModelsPopularityService) {
        this.vModelsPopularityService = vModelsPopularityService;
    }

    @PreAuthorize("hasAuthority('VIEW_MODELS_POPULARITY') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/top-selling")
    public ResponseEntity<List<VModelsPopularityDto>> getTopSellingModels() {
        return ResponseEntity.ok(vModelsPopularityService.getTopSellingModels());
    }

    @PreAuthorize("hasAuthority('VIEW_MODELS_POPULARITY') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/most-ordered")
    public ResponseEntity<List<VModelsPopularityDto>> getMostOrderedModels() {
        return ResponseEntity.ok(vModelsPopularityService.getMostOrderedModels());
    }
}
