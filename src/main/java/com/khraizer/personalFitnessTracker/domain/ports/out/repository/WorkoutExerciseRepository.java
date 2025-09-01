package com.khraizer.personalFitnessTracker.domain.ports.out.repository;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutExercise;

import java.util.List;
import java.util.Optional;

/**
 * Output port interface for WorkoutExercise repository.
 * Defines operations for managing WorkoutExercise persistence.
 */
public interface WorkoutExerciseRepository {

    /**
     * Retrieves all WorkoutExercises.
     */
    Optional<List<WorkoutExercise>> getAll();

    /**
     * Finds all WorkoutExercises associated with a specific Workout.
     */
    Optional<List<WorkoutExercise>> findByWorkout(Workout workout);

    /**
     * Saves a new WorkoutExercise.
     */
    void save(WorkoutExercise workoutExercise);
}
