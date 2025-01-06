package com.threed_model_market.project.exception_handler;

import com.threed_model_market.project.exception_handler.exceptions.*;
import com.threed_model_market.project.exception_handler.exceptions.User.UserAlreadyExists;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
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

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleAlreadyExistsException(UserNotFoundException ex) {
        logger.error("User already exists: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserInvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(UserNotFoundException ex) {
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
}
