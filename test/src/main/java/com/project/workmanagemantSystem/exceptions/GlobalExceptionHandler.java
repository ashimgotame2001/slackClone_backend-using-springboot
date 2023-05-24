package com.project.workmanagemantSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadAlertException.class)
    public ResponseEntity<?> handleBadAlertException(BadAlertException ex) {
        // Create the response body
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDetail(ex.getDetail());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorKey(ex.getErrorKey());
        errorResponse.setPath(ex.getInstance());

        // Set any additional fields or properties in the error response as needed

        // Return the ResponseEntity with the error response and the appropriate HTTP status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
