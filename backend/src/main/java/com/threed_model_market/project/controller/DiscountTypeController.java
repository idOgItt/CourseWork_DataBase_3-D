package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.DiscountTypeDto;
import com.threed_model_market.project.model.DiscountType;
import com.threed_model_market.project.service.DiscountTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount-types")
public class DiscountTypeController {

    private final DiscountTypeService discountTypeService;

    public DiscountTypeController(DiscountTypeService discountTypeService) {
        this.discountTypeService = discountTypeService;
    }

    @PreAuthorize("hasAuthority('MANAGE_DISCOUNT_TYPES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<DiscountType> createDiscountType(@RequestBody DiscountTypeDto discountTypeDto) {
        return ResponseEntity.ok(discountTypeService.createDiscountType(discountTypeDto));
    }

    @PreAuthorize("hasAuthority('VIEW_DISCOUNT_TYPES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<DiscountType>> getAllDiscountTypes() {
        return ResponseEntity.ok(discountTypeService.getAllDiscountTypes());
    }

    @PreAuthorize("hasAuthority('VIEW_DISCOUNT_TYPES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DiscountType> getDiscountTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(discountTypeService.getDiscountTypeById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_DISCOUNT_TYPES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeDiscountType(@PathVariable Long id) {
        discountTypeService.removeDiscountType(id);
        return ResponseEntity.noContent().build();
    }
}
