package com.threed_model_market.project.exception_handler.exceptions.ModelStatus;

public class ModelStatusNotFoundException extends RuntimeException {
    public ModelStatusNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ModelStatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
