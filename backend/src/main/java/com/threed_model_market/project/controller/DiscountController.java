package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.DiscountDto;
import com.threed_model_market.project.model.Discount;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.DiscountService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public DiscountController(DiscountService discountService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.discountService = discountService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('MANAGE_DISCOUNTS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Discount> createDiscount(@RequestBody DiscountDto discountDto, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        return ResponseEntity.ok(discountService.createDiscount(discountDto));
    }

    @PreAuthorize("hasAuthority('VIEW_DISCOUNTS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @PreAuthorize("hasAuthority('VIEW_DISCOUNTS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Integer id) {
        return ResponseEntity.ok(discountService.getDiscountById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_DISCOUNTS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeDiscount(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        discountService.removeDiscount(id);
        return ResponseEntity.noContent().build();
    }
}
