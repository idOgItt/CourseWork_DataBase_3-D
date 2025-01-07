package com.threed_model_market.project.exception_handler.exceptions.UserImage;

public class UserImageNotFoundException extends RuntimeException {
    public UserImageNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public UserImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
