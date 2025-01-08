package com.threed_model_market.project.exception_handler;

import com.threed_model_market.project.exception_handler.exceptions.Model.ModelNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.ModelStatus.ModelStatusNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ModelExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleModelNotFoundException(ModelNotFoundException ex) {
        logger.error("Model not found: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ModelStatusNotFoundException.class)
    public ResponseEntity<String> handleModelNotFoundException(ModelStatusNotFoundException ex) {
        logger.error("ModelStatus not found: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}