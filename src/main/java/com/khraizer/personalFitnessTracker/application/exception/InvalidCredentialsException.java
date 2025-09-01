package com.khraizer.personalFitnessTracker.application.exception;

/**
 * Exception thrown when user authentication fails due to invalid credentials.
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
