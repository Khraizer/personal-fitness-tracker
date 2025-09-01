package com.khraizer.personalFitnessTracker.infraestructure.output.mapper;

import com.khraizer.personalFitnessTracker.domain.model.WorkoutExercise;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutExerciseJpaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between WorkoutExercise domain models and JPA entities.
 */
@Component
public class WorkoutExerciseMapperInfrastructure {

    private final WorkoutMapperInfrastructure workoutMapperInfrastructure;
    private final ExerciseMapperInfrastructure exerciseMapperInfrastructure;

    public WorkoutExerciseMapperInfrastructure(
            WorkoutMapperInfrastructure workoutMapperInfrastructure,
            ExerciseMapperInfrastructure exerciseMapperInfrastructure
    ) {
        this.workoutMapperInfrastructure = workoutMapperInfrastructure;
        this.exerciseMapperInfrastructure = exerciseMapperInfrastructure;
    }

    /**
     * Converts a JPA entity to a domain model.
     */
    public WorkoutExercise toModel(WorkoutExerciseJpaEntity entity) {
        return new WorkoutExercise(
                entity.getId(),
                workoutMapperInfrastructure.toModel(entity.getWorkout()),
                exerciseMapperInfrastructure.toModel(entity.getExercise()),
                entity.getSets(),
                entity.getQuantity()
        );
    }

    /**
     * Converts a domain model to a JPA entity.
     */
    public WorkoutExerciseJpaEntity toEntity(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseJpaEntity(
                workoutExercise.getId(),
                workoutMapperInfrastructure.toEntity(workoutExercise.getWorkout()),
                exerciseMapperInfrastructure.toEntity(workoutExercise.getExercise()),
                workoutExercise.getSets(),
                workoutExercise.getQuantity()
        );
    }
}
