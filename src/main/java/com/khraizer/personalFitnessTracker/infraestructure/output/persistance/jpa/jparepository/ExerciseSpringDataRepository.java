package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.ExerciseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository interface for ExerciseJpaEntity.
 * Provides CRUD operations and custom queries for Exercise entities.
 */
public interface ExerciseSpringDataRepository extends JpaRepository<ExerciseJpaEntity, Long> {

    /**
     * Finds an Exercise entity by name ignoring case.
     */
    Optional<ExerciseJpaEntity> findByNameIgnoreCase(String name);

    /**
     * Finds exercises that are not associated with the specified workout.
     * Useful to retrieve available exercises for a workout.
     */
    @Query("SELECT e FROM ExerciseJpaEntity e " +
            "WHERE e.id NOT IN (" +
            "  SELECT we.exercise.id FROM WorkoutExerciseJpaEntity we WHERE we.workout.id = :id" +
            ")")
    List<ExerciseJpaEntity> findAvailable(@Param("id") Long id);
}
