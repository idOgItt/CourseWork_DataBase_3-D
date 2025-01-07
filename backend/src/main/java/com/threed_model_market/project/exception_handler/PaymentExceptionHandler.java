package com.threed_model_market.project.exception_handler;

import com.threed_model_market.project.exception_handler.exceptions.PaymentMethod.PaymentMethodNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.PaymentStatus.PaymentStatusNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PaymentExceptionHandler {
    @ExceptionHandler(PaymentMethodNotFoundException.class)
    public ResponseEntity<String> handlePaymentMethodNotFoundException(PaymentMethodNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PaymentStatusNotFoundException.class)
    public ResponseEntity<String> handlePaymentStatusNotFoundException(PaymentStatusNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
