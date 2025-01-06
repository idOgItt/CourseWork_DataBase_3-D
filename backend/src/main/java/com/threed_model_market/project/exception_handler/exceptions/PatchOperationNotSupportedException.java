package com.threed_model_market.project.exception_handler.exceptions;

public class PatchOperationNotSupportedException extends RuntimeException {
    public PatchOperationNotSupportedException(String message) {
        super(message);
    }

    public PatchOperationNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
