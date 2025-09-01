package com.khraizer.personalFitnessTracker.application.usecase;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutLogExercisePortIn;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutLogExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Use case implementation for WorkoutLogExercise-related operations.
 * Acts as intermediary between application layer and repository.
 */
@Service
public class WorkoutLogExerciseUseCase implements WorkoutLogExercisePortIn {

    private final WorkoutLogExerciseRepository repository;

    public WorkoutLogExerciseUseCase(WorkoutLogExerciseRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds all WorkoutLogExercise entries for a given workout log and user.
     */
    @Override
    public Optional<List<WorkoutLogExercise>> findByWorkoutLog(WorkoutLog workoutLog, User user) {
        return repository.findByWorkoutLog(workoutLog, user);
    }

    /**
     * Saves a WorkoutLogExercise entity to the repository.
     */
    @Override
    public void save(WorkoutLogExercise workoutLogExercise) {
        repository.save(workoutLogExercise);
    }
}
