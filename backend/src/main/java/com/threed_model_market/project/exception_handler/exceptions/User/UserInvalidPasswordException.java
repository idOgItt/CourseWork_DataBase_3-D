package com.threed_model_market.project.exception_handler.exceptions.User;

public class UserInvalidPasswordException extends RuntimeException {
    public UserInvalidPasswordException(String message) {
        super(message);
    }

    public UserInvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
