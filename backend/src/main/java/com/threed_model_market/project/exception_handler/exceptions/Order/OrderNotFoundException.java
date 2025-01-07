package com.threed_model_market.project.exception_handler.exceptions.Order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
