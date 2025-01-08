package com.threed_model_market.project.exception_handler.exceptions.OrderStatus;

public class OrderStatusNotFoundException extends RuntimeException {
    public OrderStatusNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public OrderStatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
