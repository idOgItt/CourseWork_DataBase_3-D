package com.threed_model_market.project.exception_handler.exceptions.PaymentMethod;

public class PaymentMethodNotFoundException extends RuntimeException {
    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public PaymentMethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
