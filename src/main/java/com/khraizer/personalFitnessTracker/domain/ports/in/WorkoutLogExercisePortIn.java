package com.khraizer.personalFitnessTracker.domain.ports.in;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;

import java.util.List;
import java.util.Optional;

/**
 * Input port interface for WorkoutLogExercise use cases.
 * Defines operations for managing WorkoutLogExercises.
 */
public interface WorkoutLogExercisePortIn {

    /**
     * Finds WorkoutLogExercises by a specific WorkoutLog and User.
     */
    Optional<List<WorkoutLogExercise>> findByWorkoutLog(WorkoutLog workoutLog, User user);

    /**
     * Saves a new WorkoutLogExercise.
     */
    void save(WorkoutLogExercise workoutLogExercise);
}
