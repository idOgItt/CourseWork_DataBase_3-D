package com.threed_model_market.project.exception_handler;

import com.threed_model_market.project.exception_handler.exceptions.Patch.PatchOperationNotSupportedException;
import com.threed_model_market.project.exception_handler.exceptions.User.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("User not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        logger.error("User already exists: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserInvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(UserInvalidPasswordException ex) {
        logger.error("Invalid password for user: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PatchOperationNotSupportedException.class)
    public ResponseEntity<String> handlePatchOperationNotSupportedException(PatchOperationNotSupportedException ex) {
        logger.error("Patch operation not supported: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNotFoundMailException.class)
    public ResponseEntity<String> handleUserNotFoundMailException(UserNotFoundMailException ex) {
        logger.error("User not found by email: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserInvalidMailFormatException.class)
    public ResponseEntity<String> handleUserInvalidMailFormatException(UserInvalidMailFormatException ex) {
        logger.error("Invalid email format: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
