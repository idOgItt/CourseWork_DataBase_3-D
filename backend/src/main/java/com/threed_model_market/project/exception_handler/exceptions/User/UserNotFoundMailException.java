package com.threed_model_market.project.exception_handler.exceptions.User;

public class UserNotFoundMailException extends RuntimeException {
    public UserNotFoundMailException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public UserNotFoundMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
