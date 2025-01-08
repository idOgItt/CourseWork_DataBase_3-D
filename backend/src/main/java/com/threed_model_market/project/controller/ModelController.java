package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.ModelDto;
import com.threed_model_market.project.model.Model;
import com.threed_model_market.project.service.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Model>> getModelsByCategoryId(@PathVariable Long categoryId) {
        List<Model> models = modelService.getModelsByCategoryId(categoryId);
        return ResponseEntity.ok(models);
    }

    @PreAuthorize("hasAuthority('MANAGE_MODELS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Model> createModel(@RequestBody ModelDto modelDto) {
        return ResponseEntity.ok(modelService.createModel(modelDto));
    }

    @PreAuthorize("hasAuthority('VIEW_MODELS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Model>> getAllModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModelById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Model>> getModelsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(modelService.getModelsByUserId(userId));
    }

    @PreAuthorize("hasAuthority('MANAGE_MODELS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('MANAGE_MODELS') or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody ModelDto modelDto) {
        return ResponseEntity.ok(modelService.updateModel(id, modelDto));
    }
}
