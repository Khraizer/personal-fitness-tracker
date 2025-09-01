package com.khraizer.personalFitnessTracker.application.usecase;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutPortIn;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Use case implementation for Workout-related operations.
 * Serves as the bridge between the application layer and the Workout repository.
 */
@Service
public class WorkoutUseCase implements WorkoutPortIn {

    private final WorkoutRepository repository;

    public WorkoutUseCase(WorkoutRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all workouts from the repository.
     * Returns Optional.empty() if no workouts found.
     */
    @Override
    public Optional<List<Workout>> getAll() {
        return repository.getAll().isPresent() ? Optional.of(repository.getAll().get()) : Optional.empty();
    }

    /**
     * Finds a workout by its name.
     */
    @Override
    public Optional<Workout> findByName(String name) {
        return repository.findByName(name);
    }

    /**
     * Saves a workout to the repository.
     */
    @Override
    public void save(Workout workout) {
        repository.save(workout);
    }
}
