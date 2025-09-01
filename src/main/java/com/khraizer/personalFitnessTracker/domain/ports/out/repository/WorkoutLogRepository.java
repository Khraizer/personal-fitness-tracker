package com.khraizer.personalFitnessTracker.domain.ports.out.repository;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;

import java.util.List;
import java.util.Optional;

/**
 * Output port interface for WorkoutLog repository.
 * Defines operations for managing WorkoutLog persistence.
 */
public interface WorkoutLogRepository {

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
