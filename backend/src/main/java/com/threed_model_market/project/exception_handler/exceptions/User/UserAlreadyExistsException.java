package com.threed_model_market.project.exception_handler.exceptions.User;

public class UserAlreadyExistsException extends Throwable {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
