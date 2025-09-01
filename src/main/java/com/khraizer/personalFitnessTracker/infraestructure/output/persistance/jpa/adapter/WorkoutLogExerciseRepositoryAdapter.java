package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.adapter;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.WorkoutLogExerciseRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutLogExerciseMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.WorkoutLogMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.ExerciseJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogExerciseJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Adapter class implementing WorkoutLogExerciseRepository.
 * Manages persistence operations for WorkoutLogExercise entities using JPA repositories.
 * Maps between domain models and JPA entities via infrastructure mappers.
 */
@Repository
public class WorkoutLogExerciseRepositoryAdapter implements WorkoutLogExerciseRepository {

    private final WorkoutLogExerciseSpringDataRepository repository;
    private final RoleSpringDataRepository roleSpringDataRepository;
    private final WorkoutLogSpringDataRepository workoutLogSpringDataRepository;
    private final UserSpringDataRepository userSpringDataRepository;
    private final WorkoutLogMapperInfrastructure workoutLogMapperInfrastructure;
    private final ExerciseSpringDataRepository exerciseSpringDataRepository;
    private final WorkoutLogExerciseMapperInfrastructure workoutLogExerciseMapper;

    /**
     * Constructor for dependency injection.
     */
    public WorkoutLogExerciseRepositoryAdapter(
            WorkoutLogExerciseSpringDataRepository repository,
            RoleSpringDataRepository roleSpringDataRepository,
            WorkoutLogSpringDataRepository workoutLogSpringDataRepository,
            UserSpringDataRepository userSpringDataRepository,
            WorkoutLogMapperInfrastructure workoutLogMapperInfrastructure,
            ExerciseSpringDataRepository exerciseSpringDataRepository,
            WorkoutLogExerciseMapperInfrastructure workoutLogExerciseMapper
    ) {
        this.repository = repository;
        this.roleSpringDataRepository = roleSpringDataRepository;
        this.workoutLogSpringDataRepository = workoutLogSpringDataRepository;
        this.userSpringDataRepository = userSpringDataRepository;
        this.workoutLogMapperInfrastructure = workoutLogMapperInfrastructure;
        this.exerciseSpringDataRepository = exerciseSpringDataRepository;
        this.workoutLogExerciseMapper = workoutLogExerciseMapper;
    }

    /**
     * Retrieves all WorkoutLogExercise entities from the database,
     * maps them to domain models, and includes exercise time information.
     */
    @Override
    public Optional<List<WorkoutLogExercise>> getAll() {
        List<WorkoutLogExerciseJpaEntity> workoutLogExercises = repository.findAll();
        Map<String, Integer> exercises = new HashMap<>();
        workoutLogExercises.forEach(workoutLogExerciseJpaEntity ->
                exercises.put(workoutLogExerciseJpaEntity.getWorkoutExercise().getName(), workoutLogExerciseJpaEntity.getTime())
        );
        return Optional.of(workoutLogExercises.stream()
                .map(workoutLogExerciseJpaEntity -> workoutLogExerciseMapper.toModel(workoutLogExerciseJpaEntity, exercises))
                .toList());
    }

    /**
     * Finds all WorkoutLogExercise entities linked to a specific WorkoutLog.
     * Throws RuntimeException if the WorkoutLog is not found.
     * Returns mapped domain models wrapped in an Optional.
     */
    @Override
    public Optional<List<WorkoutLogExercise>> findByWorkoutLog(WorkoutLog workoutLog, User user) {
        WorkoutLogJpaEntity workoutLogJpaEntity = workoutLogSpringDataRepository
                .findById(workoutLog.getId())
                .orElseThrow(() -> new RuntimeException("WorkoutLog not found"));

        List<WorkoutLogExerciseJpaEntity> workoutLogExercises = repository.findByWorkoutLog(workoutLogJpaEntity);

        Map<String, Integer> exercises = new HashMap<>();
        workoutLogExercises.forEach(e -> exercises.put(e.getWorkoutExercise().getName(), e.getTime()));

        List<WorkoutLogExercise> mappedExercises = workoutLogExercises.stream()
                .map(e -> workoutLogExerciseMapper.toModel(e, exercises))
                .toList();

        return Optional.of(mappedExercises);
    }

    /**
     * Saves a WorkoutLogExercise domain model to the database.
     * Converts domain model to JPA entity, sets associations, and persists.
     */
    @Override
    public void save(WorkoutLogExercise workoutLogExercise) {
        WorkoutLogJpaEntity workoutLogJpaEntity = workoutLogSpringDataRepository.findById(workoutLogExercise.getWorkoutLog().getId()).get();
        ExerciseJpaEntity exerciseJpaEntity = exerciseSpringDataRepository.findByNameIgnoreCase(workoutLogExercise.getWorkoutExercise().name()).get();

        WorkoutLogExerciseJpaEntity workoutLogExerciseJpaEntity = workoutLogExerciseMapper.toEntity(
                workoutLogExercise,
                roleSpringDataRepository.findByRole(workoutLogExercise.getWorkoutLog().getUser().getRole().name()).get()
        );

        workoutLogExerciseJpaEntity.setWorkoutLog(workoutLogJpaEntity);
        workoutLogExerciseJpaEntity.setWorkoutExercise(exerciseJpaEntity);

        repository.save(workoutLogExerciseJpaEntity);
    }
}
