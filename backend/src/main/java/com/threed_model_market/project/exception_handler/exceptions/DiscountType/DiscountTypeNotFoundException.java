package com.threed_model_market.project.exception_handler.exceptions.DiscountType;

public class DiscountTypeNotFoundException extends RuntimeException {
    public DiscountTypeNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public DiscountTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}