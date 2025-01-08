package com.threed_model_market.project.exception_handler.exceptions.Log;

public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public LogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
