package com.threed_model_market.project.exception_handler.exceptions.PaymentStatus;

public class PaymentStatusNotFoundException extends RuntimeException {
    public PaymentStatusNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public PaymentStatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
