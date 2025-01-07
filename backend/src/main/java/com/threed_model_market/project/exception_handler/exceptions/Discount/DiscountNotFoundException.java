package com.threed_model_market.project.exception_handler.exceptions.Discount;

public class DiscountNotFoundException extends RuntimeException {
    public DiscountNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public DiscountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}