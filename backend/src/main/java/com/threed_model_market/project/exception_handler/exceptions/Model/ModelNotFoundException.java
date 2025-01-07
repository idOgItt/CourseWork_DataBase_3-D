package com.threed_model_market.project.exception_handler.exceptions.Model;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
