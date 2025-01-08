package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.ModelStatusDto;
import com.threed_model_market.project.model.ModelStatus;
import com.threed_model_market.project.service.ModelStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model-statuses")
public class ModelStatusController {

    private final ModelStatusService modelStatusService;

    public ModelStatusController(ModelStatusService modelStatusService) {
        this.modelStatusService = modelStatusService;
    }

    @PreAuthorize("hasAuthority('MANAGE_MODEL_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<ModelStatus> createModelStatus(@RequestBody ModelStatusDto modelStatusDto) {
        ModelStatus modelStatus = modelStatusService.createModelStatus(modelStatusDto);
        return ResponseEntity.ok(modelStatus);
    }

    @PreAuthorize("hasAuthority('VIEW_MODEL_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<ModelStatus>> getAllModelStatuses() {
        return ResponseEntity.ok(modelStatusService.getAllModelStatuses());
    }

    @PreAuthorize("hasAuthority('VIEW_MODEL_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ModelStatus> getModelStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(modelStatusService.getModelStatusById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_MODEL_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeModelStatus(@PathVariable Long id) {
        modelStatusService.removeModelStatus(id);
        return ResponseEntity.noContent().build();
    }
}
