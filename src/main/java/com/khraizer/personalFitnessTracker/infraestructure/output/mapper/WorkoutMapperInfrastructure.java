package com.khraizer.personalFitnessTracker.infraestructure.output.mapper;

import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutJpaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Workout domain models and JPA entities.
 */
@Component
public class WorkoutMapperInfrastructure {

    /**
     * Converts a Workout JPA entity to a Workout domain model.
     */
    public Workout toModel(WorkoutJpaEntity entity) {
        return new Workout(entity.getId(), entity.getName(), entity.getDescription());
    }

    /**
     * Converts a Workout domain model to a Workout JPA entity.
     */
    public WorkoutJpaEntity toEntity(Workout model) {
        return new WorkoutJpaEntity(model.id(), model.name(), model.description());
    }
}
