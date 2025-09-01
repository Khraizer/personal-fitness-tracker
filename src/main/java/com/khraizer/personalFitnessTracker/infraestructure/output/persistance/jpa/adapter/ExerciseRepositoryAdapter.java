package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.adapter;

import com.khraizer.personalFitnessTracker.domain.model.Exercise;
import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.ExerciseRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.ExerciseMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.ExerciseJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.ExerciseSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class that implements ExerciseRepository interface.
 * It acts as a bridge between the domain layer and the JPA infrastructure layer.
 * Uses ExerciseMapperInfrastructure to convert between domain models and JPA entities.
 * Uses ExerciseSpringDataRepository to perform CRUD operations on the database.
 */
@Repository
public class ExerciseRepositoryAdapter implements ExerciseRepository {

    private final ExerciseMapperInfrastructure mapper;
    private final ExerciseSpringDataRepository repository;

    /**
     * Constructor to inject dependencies.
     */
    public ExerciseRepositoryAdapter(ExerciseMapperInfrastructure mapper, ExerciseSpringDataRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Saves the given Exercise domain model to the database.
     */
    @Override
    public void save(Exercise exercise) {
        repository.save(mapper.toEntity(exercise));
    }

    /**
     * Retrieves all Exercise entities from the database,
     * converts them to domain models and returns the list.
     */
    @Override
    public List<Exercise> getAll() {
        return repository.findAll().stream().map(mapper::toModel).toList();
    }

    /**
     * Finds all Exercises available for a specific Workout.
     * Converts the JPA entities to domain models and returns them.
     */
    @Override
    public List<Exercise> findAvailable(Workout workout) {
        return repository.findAvailable(workout.id()).stream().map(mapper::toModel).toList();
    }

    /**
     * Finds an Exercise by its name, ignoring case.
     * Returns an Optional containing the domain model if found, or empty if not.
     */
    @Override
    public Optional<Exercise> findByName(String name) {
        Optional<ExerciseJpaEntity> exerciseJpaEntity = repository.findByNameIgnoreCase(name);
        if (exerciseJpaEntity.isPresent()) {
            return Optional.of(mapper.toModel(exerciseJpaEntity.get()));
        } else {
            return Optional.empty();
        }
    }
}
