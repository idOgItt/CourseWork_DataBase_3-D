package com.threed_model_market.project.exception_handler.exceptions.Permission;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public PermissionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
