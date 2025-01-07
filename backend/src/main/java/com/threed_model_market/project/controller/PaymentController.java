package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.PaymentDto;
import com.threed_model_market.project.model.Payment;
import com.threed_model_market.project.security.JwtTokenProvider;
import com.threed_model_market.project.service.PaymentService;
import com.threed_model_market.project.util.AccessValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessValidator accessValidator;

    public PaymentController(PaymentService paymentService, JwtTokenProvider jwtTokenProvider, AccessValidator accessValidator) {
        this.paymentService = paymentService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessValidator = accessValidator;
    }

    @PreAuthorize("hasAuthority('CREATE_PAYMENTS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDto paymentDto, @RequestHeader("Authorization") String token) {
        Long userIdFromToken = jwtTokenProvider.getUserIdFromJWT(token);
        accessValidator.validateUserAccess(token, userIdFromToken);

        Payment payment = paymentService.createPayment(paymentDto);
        return ResponseEntity.ok(payment);
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENTS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Payment>> getPaymentsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentsByOrderId(orderId));
    }

    @PreAuthorize("hasAuthority('DELETE_PAYMENTS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, jwtTokenProvider.getUserIdFromJWT(token));
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
