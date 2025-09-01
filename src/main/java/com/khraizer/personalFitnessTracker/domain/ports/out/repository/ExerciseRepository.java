package com.khraizer.personalFitnessTracker.domain.ports.out.repository;

import com.khraizer.personalFitnessTracker.domain.model.Exercise;
import com.khraizer.personalFitnessTracker.domain.model.Workout;

import java.util.List;
import java.util.Optional;

/**
 * Output port interface for Exercise repository.
 * Defines operations for managing Exercise persistence.
 */
public interface ExerciseRepository {

    /**
     * Saves a new Exercise.
     */
    void save(Exercise exercise);

    /**
     * Retrieves all Exercises.
     */
    List<Exercise> getAll();

    /**
     * Finds Exercises that are available for a given Workout.
     */
    List<Exercise> findAvailable(Workout workout);

    /**
     * Finds an Exercise by its name.
     */
    Optional<Exercise> findByName(String name);
}
