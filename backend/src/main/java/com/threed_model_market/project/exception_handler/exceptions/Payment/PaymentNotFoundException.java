package com.threed_model_market.project.exception_handler.exceptions.Payment;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
