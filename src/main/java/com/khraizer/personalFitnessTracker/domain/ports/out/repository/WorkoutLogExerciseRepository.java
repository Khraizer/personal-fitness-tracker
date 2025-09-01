package com.khraizer.personalFitnessTracker.domain.ports.out.repository;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;

import java.util.List;
import java.util.Optional;

/**
 * Output port interface for WorkoutLogExercise repository.
 * Defines operations for managing WorkoutLogExercise persistence.
 */
public interface WorkoutLogExerciseRepository {

    /**
     * Retrieves all WorkoutLogExercises.
     */
    Optional<List<WorkoutLogExercise>> getAll();

    /**
     * Finds all WorkoutLogExercises associated with a specific WorkoutLog and User.
     */
    Optional<List<WorkoutLogExercise>> findByWorkoutLog(WorkoutLog workoutLog, User user);

    /**
     * Saves a new WorkoutLogExercise.
     */
    void save(WorkoutLogExercise workoutLogExercise);
}
