package com.khraizer.personalFitnessTracker.domain.ports.out.repository;

import com.khraizer.personalFitnessTracker.domain.model.Workout;

import java.util.List;
import java.util.Optional;

/**
 * Output port interface for Workout repository.
 * Defines operations for managing Workout persistence.
 */
public interface WorkoutRepository {

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
