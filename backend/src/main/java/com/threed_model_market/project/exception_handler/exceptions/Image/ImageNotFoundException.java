package com.threed_model_market.project.exception_handler.exceptions.Image;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
