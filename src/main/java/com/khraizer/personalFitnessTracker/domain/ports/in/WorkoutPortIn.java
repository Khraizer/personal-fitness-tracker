package com.khraizer.personalFitnessTracker.domain.ports.in;

import com.khraizer.personalFitnessTracker.domain.model.Workout;

import java.util.List;
import java.util.Optional;

/**
 * Input port interface for Workout use cases.
 * Defines operations for managing Workouts.
 */
public interface WorkoutPortIn {

    /**
     * Retrieves all Workouts.
     */
    Optional<List<Workout>> getAll();

    /**
     * Finds a Workout by its name.
     */
    Optional<Workout> findByName(String name);

    /**
     * Saves a new Workout.
     */
    void save(Workout workout);
}
