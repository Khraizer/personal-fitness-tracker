package com.khraizer.personalFitnessTracker.infraestructure.output.mapper;

import com.khraizer.personalFitnessTracker.domain.model.Exercise;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.ExerciseJpaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Exercise domain models and Exercise JPA entities.
 */
@Component
public class ExerciseMapperInfrastructure {

    /**
     * Converts a JPA entity to a domain model.
     */
    public Exercise toModel(ExerciseJpaEntity entity) {
        return new Exercise(entity.getId(), entity.getName(), entity.getMet());
    }

    /**
     * Converts a domain model to a JPA entity.
     */
    public ExerciseJpaEntity toEntity(Exercise exercise) {
        return new ExerciseJpaEntity(exercise.id(), exercise.name(), exercise.met());
    }
}
