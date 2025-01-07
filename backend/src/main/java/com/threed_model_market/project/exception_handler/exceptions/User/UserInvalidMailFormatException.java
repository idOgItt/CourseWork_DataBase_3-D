package com.threed_model_market.project.exception_handler.exceptions.User;

public class UserInvalidMailFormatException extends RuntimeException {
    public UserInvalidMailFormatException(String message) {
        super(message);
    }

    public UserInvalidMailFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
