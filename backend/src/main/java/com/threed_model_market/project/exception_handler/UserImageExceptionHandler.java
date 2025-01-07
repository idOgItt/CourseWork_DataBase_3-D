package com.threed_model_market.project.exception_handler;

import com.threed_model_market.project.exception_handler.exceptions.UserImage.UserImageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserImageExceptionHandler {

    @ExceptionHandler(UserImageNotFoundException.class)
    public ResponseEntity<String> handleUserImageNotFoundException(UserImageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
