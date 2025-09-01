package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository interface for UserJpaEntity.
 * Provides CRUD operations and a custom method to find a user by email.
 */
public interface UserSpringDataRepository extends JpaRepository<UserJpaEntity, UUID> {

    /**
     * Finds a User entity by its email.
     *
     * @param email the email address to search for.
     * @return Optional containing the UserJpaEntity if found, else empty.
     */
    Optional<UserJpaEntity> findByEmail(String email);
}
