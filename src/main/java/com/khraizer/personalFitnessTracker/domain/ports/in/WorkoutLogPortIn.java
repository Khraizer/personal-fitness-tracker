package com.khraizer.personalFitnessTracker.domain.ports.in;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;

import java.util.List;
import java.util.Optional;

/**
 * Input port interface for WorkoutLog use cases.
 * Defines operations for managing WorkoutLogs.
 */
public interface WorkoutLogPortIn {

    /**
     * Saves a new WorkoutLog.
     */
    WorkoutLog save(WorkoutLog workoutLog);

    /**
     * Retrieves all WorkoutLogs.
     */
    Optional<List<WorkoutLog>> getAll();

    /**
     * Finds all WorkoutLogs associated with a specific User.
     */
    List<WorkoutLog> findByUser(User user);

    /**
     * Updates an existing WorkoutLog.
     */
    void update(WorkoutLog workoutLog);
}
