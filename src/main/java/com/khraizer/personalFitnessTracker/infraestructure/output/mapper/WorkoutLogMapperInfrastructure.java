package com.khraizer.personalFitnessTracker.infraestructure.output.mapper;

import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.RoleJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.UserJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Mapper for converting between WorkoutLog domain models and JPA entities.
 */
@Component
public class WorkoutLogMapperInfrastructure {

    private final UserMapperInfrastructure userMapperInfrastructure;
    private final WorkoutMapperInfrastructure workoutMapperInfrastructure;

    public WorkoutLogMapperInfrastructure(
            UserMapperInfrastructure userMapperInfrastructure,
            WorkoutMapperInfrastructure workoutMapperInfrastructure
    ) {
        this.userMapperInfrastructure = userMapperInfrastructure;
        this.workoutMapperInfrastructure = workoutMapperInfrastructure;
    }

    /**
     * Converts a WorkoutLog domain model to a WorkoutLog JPA entity.
     */
    public WorkoutLogJpaEntity toEntity(WorkoutLog workoutLog, RoleJpaEntity roleJpaEntity) {
        WorkoutJpaEntity workoutEntity = workoutLog.getWorkout() != null
                ? workoutMapperInfrastructure.toEntity(workoutLog.getWorkout())
                : null;

        UserJpaEntity userEntity = workoutLog.getUser() != null
                ? userMapperInfrastructure.toEntity(workoutLog.getUser(), roleJpaEntity)
                : null;

        WorkoutLogJpaEntity entity = new WorkoutLogJpaEntity(
                userEntity,
                workoutEntity,
                workoutLog.getDate(),
                workoutLog.getCalories(),
                workoutLog.getTime()
        );

        if (workoutLog.getId() != null) {
            entity.setId(workoutLog.getId());
        }

        return entity;
    }

    /**
     * Converts a WorkoutLog JPA entity to a WorkoutLog domain model.
     */
    public WorkoutLog toModel(WorkoutLogJpaEntity workoutLogJpaEntity, Map<String, Integer> exerciseTime) {
        return WorkoutLog.builder()
                .id(workoutLogJpaEntity.getId())
                .user(userMapperInfrastructure.toModel(workoutLogJpaEntity.getUser()))
                .workout(workoutMapperInfrastructure.toModel(workoutLogJpaEntity.getWorkout()))
                .date(workoutLogJpaEntity.getDate())
                .exerciseTime(exerciseTime)
                .calories(workoutLogJpaEntity.getCalories())
                .time(workoutLogJpaEntity.getTime())
                .build();
    }
}
