package com.threed_model_market.project.exception_handler.exceptions.Patch;

public class PatchOperationNotSupportedException extends RuntimeException {
    @SuppressWarnings("unused")
    public PatchOperationNotSupportedException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public PatchOperationNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
