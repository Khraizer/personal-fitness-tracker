package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutExerciseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository interface for WorkoutExerciseJpaEntity.
 * Provides CRUD operations and a method to find exercises associated with a specific workout.
 */
public interface WorkoutExerciseSpringDataRepository extends JpaRepository<WorkoutExerciseJpaEntity, Long> {

    /**
     * Finds all WorkoutExercise entities associated with the given workout ID.
     */
    Optional<List<WorkoutExerciseJpaEntity>> findByWorkoutId(Long id);
}
