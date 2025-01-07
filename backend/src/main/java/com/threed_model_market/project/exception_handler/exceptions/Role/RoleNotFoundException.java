package com.threed_model_market.project.exception_handler.exceptions.Role;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
