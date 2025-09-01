package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository interface for WorkoutJpaEntity.
 * Provides CRUD operations and a custom query to find a workout by name.
 */
public interface WorkoutSpringDataRepository extends JpaRepository<WorkoutJpaEntity, Long> {

    /**
     * Finds a WorkoutJpaEntity by its name.
     *
     * @param name the name of the workout.
     * @return an Optional containing the found WorkoutJpaEntity, or empty if not found.
     */
    Optional<WorkoutJpaEntity> findByName(String name);
}
