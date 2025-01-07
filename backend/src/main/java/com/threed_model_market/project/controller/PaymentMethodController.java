package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.PaymentMethodDto;
import com.threed_model_market.project.model.PaymentMethod;
import com.threed_model_market.project.service.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PreAuthorize("hasAuthority('MANAGE_PAYMENT_METHODS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(paymentMethodDto);
        return ResponseEntity.ok(paymentMethod);
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENT_METHODS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.getAllPaymentMethods());
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENT_METHODS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentMethodService.getPaymentMethodById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_PAYMENT_METHODS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePaymentMethod(@PathVariable Long id) {
        paymentMethodService.removePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
