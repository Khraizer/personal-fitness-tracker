package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.adapter;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutExercise;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutExerciseRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutExerciseMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.WorkoutExerciseSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class that implements WorkoutExerciseRepository interface.
 * Connects domain models to the JPA persistence layer using mappers.
 * Uses Spring Data JPA repositories to persist and query data.
 */
@Repository
public class WorkoutExerciseRepositoryAdapter implements WorkoutExerciseRepository {

    private final WorkoutExerciseSpringDataRepository repository;
    private final WorkoutExerciseMapperInfrastructure mapper;
    private final WorkoutMapperInfrastructure workoutMapperInfrastructure;

    /**
     * Constructor for dependency injection.
     */
    public WorkoutExerciseRepositoryAdapter(
            WorkoutExerciseSpringDataRepository repository,
            WorkoutExerciseMapperInfrastructure mapper,
            WorkoutMapperInfrastructure workoutMapperInfrastructure
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.workoutMapperInfrastructure = workoutMapperInfrastructure;
    }

    /**
     * Retrieves all workout-exercise relationships from the database.
     * Converts them to domain models and wraps them in an Optional.
     */
    @Override
    public Optional<List<WorkoutExercise>> getAll() {
        return !repository.findAll().isEmpty()
                ? Optional.of(repository.findAll().stream().map(mapper::toModel).toList())
                : Optional.empty();
    }

    /**
     * Finds all exercises assigned to a specific workout.
     * Returns an Optional list of domain models.
     */
    @Override
    public Optional<List<WorkoutExercise>> findByWorkout(Workout workout) {
        var workoutId = workoutMapperInfrastructure.toEntity(workout).getId();
        return repository.findByWorkoutId(workoutId)
                .map(list -> list.stream().map(mapper::toModel).toList());
    }

    /**
     * Saves a workout-exercise association to the database.
     */
    @Override
    public void save(WorkoutExercise workoutExercise) {
        repository.save(mapper.toEntity(workoutExercise));
    }
}
