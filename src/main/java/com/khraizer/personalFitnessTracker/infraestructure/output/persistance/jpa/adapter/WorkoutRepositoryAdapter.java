package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.adapter;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.WorkoutSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter implementing WorkoutRepository interface.
 * Handles persistence operations for Workout domain entities using Spring Data JPA.
 * Uses a mapper to convert between domain models and JPA entities.
 */
@Repository
public class WorkoutRepositoryAdapter implements WorkoutRepository {

    private final WorkoutSpringDataRepository repository;
    private final WorkoutMapperInfrastructure mapper;

    /**
     * Constructor with dependencies injected.
     * @param repository Spring Data repository for Workout entities.
     * @param mapper Mapper to convert between domain and JPA entity.
     */
    public WorkoutRepositoryAdapter(WorkoutSpringDataRepository repository, WorkoutMapperInfrastructure mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retrieves all Workout entities from the database.
     * Converts entities to domain models and returns as Optional list.
     * Returns Optional.empty() if none found.
     */
    @Override
    public Optional<List<Workout>> getAll() {
        return !repository.findAll().isEmpty() ?
                Optional.of(repository.findAll().stream().map(mapper::toModel).toList()) :
                Optional.empty();
    }

    /**
     * Finds a Workout by its name.
     * Returns an Optional containing the mapped domain model if found, else Optional.empty().
     */
    @Override
    public Optional<Workout> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::toModel);
    }

    /**
     * Saves a Workout domain model by converting it to entity and persisting it.
     */
    @Override
    public void save(Workout workout) {
        repository.save(mapper.toEntity(workout));
    }
}
