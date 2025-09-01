package com.khraizer.personalFitnessTracker.application.usecase;

import com.khraizer.personalFitnessTracker.domain.model.Exercise;
import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.ports.in.ExercisePortIn;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Use case implementation for Exercise-related operations.
 * Acts as an intermediary between the application layer and the Exercise repository.
 */
@Service
public class ExerciseUseCase implements ExercisePortIn {

    private final ExerciseRepository exerciseRepository;

    public ExerciseUseCase(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * Saves a new Exercise to the repository.
     */
    @Override
    public void save(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    /**
     * Retrieves all exercises from the repository.
     */
    @Override
    public List<Exercise> getAll() {
        return exerciseRepository.getAll();
    }

    /**
     * Retrieves exercises not yet assigned to the given workout.
     */
    @Override
    public List<Exercise> findAvailable(Workout workout) {
        return exerciseRepository.findAvailable(workout);
    }

    /**
     * Finds an exercise by its name.
     * Returns an Optional containing the exercise if found.
     */
    @Override
    public Optional<Exercise> findByName(String name) {
        return exerciseRepository.findByName(name);
    }
}
