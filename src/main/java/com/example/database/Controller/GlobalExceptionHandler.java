package com.example.database.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    /**
     * Catches wrong password or invalid username exceptions thrown during local authentication.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put(STATUS, HttpStatus.UNAUTHORIZED.value());
        errorBody.put(ERROR, "Unauthorized");
        errorBody.put(MESSAGE, "Invalid username or password. Please try again.");
        errorBody.put("path", "/auth/login");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorBody);
    }

    /**
     * Optional: Catches cases where a user isn't found in UserDetailsService.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put(STATUS, HttpStatus.UNAUTHORIZED.value());
        errorBody.put(ERROR, "Unauthorized");
        errorBody.put(MESSAGE, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorBody);
    }

    /**
     * Catches exceptions when a JWT is expired. This is crucial for the frontend to trigger a token refresh.
     */
    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleExpiredJwtException(io.jsonwebtoken.ExpiredJwtException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put(STATUS, HttpStatus.UNAUTHORIZED.value());
        errorBody.put(ERROR, "Unauthorized");
        errorBody.put(MESSAGE, "Access token has expired. Please refresh.");
        return errorBody;
    }

    /**
     * Fallback handler for general exceptions to ensure they always return JSON instead of HTML pages.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorBody.put(ERROR, "Internal Server Error");
        errorBody.put(MESSAGE, ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred.");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorBody);
    }
}