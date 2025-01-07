package com.threed_model_market.project.exception_handler.exceptions.Review;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
