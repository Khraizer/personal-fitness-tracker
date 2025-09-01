package com.khraizer.personalFitnessTracker.application.usecase;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutLogPortIn;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutLogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Use case implementation for WorkoutLog-related operations.
 * Acts as intermediary between application layer and WorkoutLog repository.
 */
@Component
public class WorkoutLogUseCase implements WorkoutLogPortIn {

    private final WorkoutLogRepository repository;

    public WorkoutLogUseCase(WorkoutLogRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves a new WorkoutLog to the repository.
     */
    @Override
    public WorkoutLog save(WorkoutLog workoutLog) {
        return repository.save(workoutLog);
    }

    /**
     * Retrieves all WorkoutLogs from the repository.
     */
    @Override
    public Optional<List<WorkoutLog>> getAll() {
        return repository.getAll();
    }

    /**
     * Finds WorkoutLogs for a specific user.
     */
    @Override
    public List<WorkoutLog> findByUser(User user) {
        return repository.findByUser(user);
    }

    /**
     * Updates an existing WorkoutLog in the repository.
     */
    @Override
    public void update(WorkoutLog workoutLog) {
        repository.update(workoutLog);
    }
}
