package com.threed_model_market.project.exception_handler.exceptions.Security;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}