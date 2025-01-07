package com.threed_model_market.project.exception_handler.exceptions.Category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
