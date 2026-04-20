package com.hospital.hms.exception;

import com.hospital.hms.dto.ApiResponse;
import com.hospital.hms.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.builder()
                        .error(new ErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST))
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleNotFound(UsernameNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body(ApiResponse.builder()
                        .error(new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND))
                        .message(ex.getMessage())
                        .build());
    }

}
