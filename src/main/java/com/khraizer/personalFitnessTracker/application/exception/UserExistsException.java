package com.khraizer.personalFitnessTracker.application.exception;

/**
 * Exception thrown when trying to register a user with an email that already exists.
 */
public class UserExistsException extends RuntimeException {
    public UserExistsException(String email) {
        super("This email " + email + " has already been registered");
    }
}
