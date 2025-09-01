package com.khraizer.personalFitnessTracker.application.usecase;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutExercise;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutExercisePortIn;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Use case implementation for WorkoutExercise-related operations.
 * Serves as the intermediary between the application layer and the WorkoutExercise repository.
 */
@Service
public class WorkoutExerciseUseCase implements WorkoutExercisePortIn {

    private final WorkoutExerciseRepository repository;

    public WorkoutExerciseUseCase(WorkoutExerciseRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all WorkoutExercise records.
     */
    @Override
    public Optional<List<WorkoutExercise>> getAll() {
        return repository.getAll();
    }

    /**
     * Retrieves all WorkoutExercise records associated with a specific workout.
     */
    @Override
    public Optional<List<WorkoutExercise>> findByWorkout(Workout workout) {
        return repository.findByWorkout(workout);
    }

    /**
     * Saves a WorkoutExercise to the repository.
     */
    @Override
    public void save(WorkoutExercise workoutExercise) {
        repository.save(workoutExercise);
    }
}
