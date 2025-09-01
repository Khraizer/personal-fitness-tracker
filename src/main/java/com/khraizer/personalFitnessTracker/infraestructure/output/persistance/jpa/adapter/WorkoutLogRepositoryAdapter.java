package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.adapter;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutLogExerciseRepository;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutLogRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.UserMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutLogMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.UserJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.UserSpringDataRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.WorkoutLogSpringDataRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.WorkoutSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Adapter implementing WorkoutLogRepository interface.
 * Manages persistence of WorkoutLog domain entities using JPA repositories.
 * Converts between domain models and JPA entities using mappers.
 */
@Repository
public class WorkoutLogRepositoryAdapter implements WorkoutLogRepository {

    private final WorkoutLogSpringDataRepository repository;
    private final WorkoutLogExerciseRepository workoutLogExerciseRepository;
    private final UserMapperInfrastructure userMapperInfrastructure;
    private final UserSpringDataRepository userSpringDataRepository;
    private final WorkoutSpringDataRepository workoutSpringDataRepository;
    private final WorkoutMapperInfrastructure workoutMapperInfrastructure;
    private final WorkoutLogMapperInfrastructure mapper;

    /**
     * Constructor for dependency injection.
     */
    public WorkoutLogRepositoryAdapter(
            WorkoutLogSpringDataRepository repository,
            WorkoutLogExerciseRepository workoutLogExerciseRepository,
            UserMapperInfrastructure userMapperInfrastructure,
            UserSpringDataRepository userSpringDataRepository,
            WorkoutSpringDataRepository workoutSpringDataRepository,
            WorkoutMapperInfrastructure workoutMapperInfrastructure,
            WorkoutLogMapperInfrastructure mapper
    ) {
        this.repository = repository;
        this.workoutLogExerciseRepository = workoutLogExerciseRepository;
        this.userMapperInfrastructure = userMapperInfrastructure;
        this.userSpringDataRepository = userSpringDataRepository;
        this.workoutSpringDataRepository = workoutSpringDataRepository;
        this.workoutMapperInfrastructure = workoutMapperInfrastructure;
        this.mapper = mapper;
    }

    /**
     * Saves a WorkoutLog entity.
     * Populates user and workout domain models by fetching from repositories.
     * Collects exercise time data from all WorkoutLogExercise entities.
     * Converts domain model to entity and persists, then returns the updated domain model.
     */
    @Override
    public WorkoutLog save(WorkoutLog workoutLog) {
        List<WorkoutLogExercise> workoutLogs = workoutLogExerciseRepository.getAll().get();
        Map<String, Integer> exerciseTime = new HashMap<>();

        workoutLog.setUser(userMapperInfrastructure.toModel(
                userSpringDataRepository.findByEmail(workoutLog.getUser().getEmail()).get())
        );

        workoutLog.setWorkout(workoutMapperInfrastructure.toModel(
                workoutSpringDataRepository.findByName(workoutLog.getWorkout().name()).get())
        );

        workoutLogs.forEach(workoutLogExercise ->
                exerciseTime.put(
                        workoutLogExercise.getWorkoutExercise().name(),
                        workoutLogExercise.getTime()
                )
        );

        return mapper.toModel(
                repository.save(
                        mapper.toEntity(workoutLog,
                                userSpringDataRepository.findByEmail(workoutLog.getUser().getEmail()).get().getRole())
                ),
                exerciseTime
        );
    }

    /**
     * Retrieves all WorkoutLog entities from the database.
     * Gathers exercise times from all WorkoutLogExercise entities.
     * Maps JPA entities to domain models and returns an Optional list.
     */
    @Override
    public Optional<List<WorkoutLog>> getAll() {
        List<WorkoutLogExercise> workoutLogs = workoutLogExerciseRepository.getAll().get();
        Map<String, Integer> exerciseTime = new HashMap<>();

        workoutLogs.forEach(workoutLogExercise ->
                exerciseTime.put(
                        workoutLogExercise.getWorkoutExercise().name(),
                        workoutLogExercise.getTime()
                )
        );

        return !repository.findAll().isEmpty() ?
                Optional.of(
                        repository.findAll().stream()
                                .map(workoutLogJpaEntity -> mapper.toModel(workoutLogJpaEntity, exerciseTime))
                                .toList()
                )
                : Optional.empty();
    }

    /**
     * Finds all WorkoutLogs for a given user.
     * Maps user to JPA entity, collects exercise times, then maps all found WorkoutLogs to domain models.
     */
    @Override
    public List<WorkoutLog> findByUser(User user) {
        UserJpaEntity userJpaEntity = userSpringDataRepository.findByEmail(user.getEmail()).get();
        List<WorkoutLogExercise> workoutLogs = workoutLogExerciseRepository.getAll().get();
        Map<String, Integer> exerciseTime = new HashMap<>();

        workoutLogs.forEach(workoutLogExercise ->
                exerciseTime.put(
                        workoutLogExercise.getWorkoutExercise().name(),
                        workoutLogExercise.getTime()
                )
        );

        return repository.findByUser(userJpaEntity).stream()
                .map(workoutLogJpaEntity -> mapper.toModel(workoutLogJpaEntity, exerciseTime))
                .toList();
    }

    /**
     * Updates an existing WorkoutLog entity.
     * Refreshes user and workout references, then saves the updated entity.
     */
    @Override
    public void update(WorkoutLog workoutLog) {
        workoutLog.setUser(userMapperInfrastructure.toModel(
                userSpringDataRepository.findByEmail(workoutLog.getUser().getEmail()).get())
        );

        workoutLog.setWorkout(workoutMapperInfrastructure.toModel(
                workoutSpringDataRepository.findByName(workoutLog.getWorkout().name()).get())
        );

        repository.save(
                mapper.toEntity(
                        workoutLog,
                        userSpringDataRepository.findByEmail(workoutLog.getUser().getEmail()).get().getRole()
                )
        );
    }
}
