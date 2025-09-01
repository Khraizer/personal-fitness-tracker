package com.khraizer.personalFitnessTracker.domain.ports.in;

import com.khraizer.personalFitnessTracker.domain.model.Exercise;
import com.khraizer.personalFitnessTracker.domain.model.Workout;

import java.util.List;
import java.util.Optional;

/**
 * Input port interface for Exercise use cases.
 * Defines the operations that can be performed related to Exercises.
 */
public interface ExercisePortIn {

    /**
     * Saves a new Exercise or updates an existing one.
     */
    void save(Exercise exercise);

    /**
     * Retrieves all Exercises.
     */
    List<Exercise> getAll();

    /**
     * Finds all Exercises that are available for a given Workout
     */
    List<Exercise> findAvailable(Workout workout);

    /**
     * Finds an Exercise by its name.
     */
    Optional<Exercise> findByName(String name);
}
