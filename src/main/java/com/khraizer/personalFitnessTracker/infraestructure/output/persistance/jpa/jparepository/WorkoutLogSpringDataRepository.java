package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.UserJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.WorkoutLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutLogSpringDataRepository extends JpaRepository<WorkoutLogJpaEntity, Long> {
    WorkoutLogJpaEntity save(WorkoutLogJpaEntity workoutLogJpaEntity);
    Optional<WorkoutLogJpaEntity> findById(Long id);
    List<WorkoutLogJpaEntity> findByUser(UserJpaEntity userJpaEntity);
}
