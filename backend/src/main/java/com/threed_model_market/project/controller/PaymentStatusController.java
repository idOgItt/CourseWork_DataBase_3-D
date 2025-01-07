package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.PaymentStatusDto;
import com.threed_model_market.project.model.PaymentStatus;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.PaymentStatusService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-statuses")
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public PaymentStatusController(PaymentStatusService paymentStatusService, JwtTokenProvider jwtTokenProvider,
                                   AccessValidator accessValidator) {
        this.paymentStatusService = paymentStatusService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('MANAGE_PAYMENT_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<PaymentStatus> createPaymentStatus(@RequestBody PaymentStatusDto paymentStatusDto,
                                                             @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        PaymentStatus paymentStatus = paymentStatusService.createPaymentStatus(paymentStatusDto);
        return ResponseEntity.ok(paymentStatus);
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENT_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<PaymentStatus>> getAllPaymentStatuses() {
        return ResponseEntity.ok(paymentStatusService.getAllPaymentStatuses());
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENT_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentStatus> getPaymentStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentStatusService.getPaymentStatusById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_PAYMENT_STATUSES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePaymentStatus(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);
        paymentStatusService.removePaymentStatus(id);
        return ResponseEntity.noContent().build();
    }
}
