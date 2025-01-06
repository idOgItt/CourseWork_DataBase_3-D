package com.threed_model_market.project.exception_handler.exceptions.User;

public class UserAlreadyExists extends Throwable {
    public UserAlreadyExists(String message) {
        super(message);
    }

    public UserAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

}
