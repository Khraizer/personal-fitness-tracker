package com.khraizer.personalFitnessTracker.infraestructure.output.mapper;

import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.RoleJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogExerciseJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Mapper for converting between WorkoutLogExercise domain models and JPA entities.
 */
@Component
public class WorkoutLogExerciseMapperInfrastructure {

    private final WorkoutLogMapperInfrastructure workoutLogMapperInfrastructure;
    private final ExerciseMapperInfrastructure exerciseMapperInfrastructure;

    public WorkoutLogExerciseMapperInfrastructure(
            WorkoutLogMapperInfrastructure workoutLogMapperInfrastructure,
            ExerciseMapperInfrastructure exerciseMapperInfrastructure
    ) {
        this.workoutLogMapperInfrastructure = workoutLogMapperInfrastructure;
        this.exerciseMapperInfrastructure = exerciseMapperInfrastructure;
    }

    /**
     * Converts a WorkoutLogExercise domain model to a JPA entity.
     */
    public WorkoutLogExerciseJpaEntity toEntity(WorkoutLogExercise workoutLogExercise, RoleJpaEntity roleJpaEntity) {
        return new WorkoutLogExerciseJpaEntity(
                workoutLogMapperInfrastructure.toEntity(workoutLogExercise.getWorkoutLog(), roleJpaEntity),
                exerciseMapperInfrastructure.toEntity(workoutLogExercise.getWorkoutExercise()),
                workoutLogExercise.getTime()
        );
    }

    /**
     * Converts a WorkoutLogExercise JPA entity to a domain model.
     */
    public WorkoutLogExercise toModel(WorkoutLogExerciseJpaEntity workoutLogExerciseJpaEntity, Map<String, Integer> exerciseTime) {
        return new WorkoutLogExercise(
                workoutLogMapperInfrastructure.toModel(workoutLogExerciseJpaEntity.getWorkoutLog(), exerciseTime),
                exerciseMapperInfrastructure.toModel(workoutLogExerciseJpaEntity.getWorkoutExercise()),
                workoutLogExerciseJpaEntity.getTime()
        );
    }
}
