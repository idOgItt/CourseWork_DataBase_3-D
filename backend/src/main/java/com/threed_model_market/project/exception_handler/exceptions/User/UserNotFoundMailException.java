package com.threed_model_market.project.exception_handler.exceptions.User;

public class UserNotFoundMailException extends Throwable {
    public UserNotFoundMailException(String message) {
        super(message);
    }

    public UserNotFoundMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
