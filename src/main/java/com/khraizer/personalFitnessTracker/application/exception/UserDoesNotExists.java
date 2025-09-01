package com.khraizer.personalFitnessTracker.application.exception;

/**
 * Exception thrown when a user with the specified email does not exist.
 */
public class UserDoesNotExists extends RuntimeException {
    public UserDoesNotExists(String email) {
        super("This email " + email + " is not associated with any user");
    }
}
