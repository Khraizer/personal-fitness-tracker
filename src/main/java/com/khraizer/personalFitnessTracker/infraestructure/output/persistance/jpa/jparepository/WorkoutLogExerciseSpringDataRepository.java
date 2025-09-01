package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogExerciseJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository interface for WorkoutLogExerciseJpaEntity.
 * Provides CRUD operations and a method to find all log exercises for a given workout log.
 */
public interface WorkoutLogExerciseSpringDataRepository extends JpaRepository<WorkoutLogExerciseJpaEntity, Long> {

    /**
     * Finds all WorkoutLogExercise entities associated with a specific WorkoutLog.
     */
    List<WorkoutLogExerciseJpaEntity> findByWorkoutLog(WorkoutLogJpaEntity workoutLogJpaEntity);
}
