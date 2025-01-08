package com.threed_model_market.project.exception_handler;

import com.threed_model_market.project.exception_handler.exceptions.Discount.DiscountNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.DiscountType.DiscountTypeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DiscountExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(DiscountNotFoundException.class)
    public ResponseEntity<String> handleDiscountNotFoundException(DiscountNotFoundException ex) {
        logger.error("Discount not found: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DiscountTypeNotFoundException.class)
    public ResponseEntity<String> handleDiscountTypeNotFoundException(DiscountTypeNotFoundException ex) {
        logger.error("DiscountType not found: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}