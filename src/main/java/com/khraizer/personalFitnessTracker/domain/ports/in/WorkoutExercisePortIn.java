package com.khraizer.personalFitnessTracker.domain.ports.in;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutExercise;

import java.util.List;
import java.util.Optional;

/**
 * Input port interface for WorkoutExercise use cases.
 * Defines operations for managing WorkoutExercises.
 */
public interface WorkoutExercisePortIn {

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
