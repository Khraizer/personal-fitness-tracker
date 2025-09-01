package com.khraizer.personalFitnessTracker.application.exception;

/**
 * Exception thrown when attempting to create or save an Exercise
 * that already exists in the system.
 */
public class ExerciseExistsException extends RuntimeException {
    public ExerciseExistsException(String message) {
        super(message);
    }
}
