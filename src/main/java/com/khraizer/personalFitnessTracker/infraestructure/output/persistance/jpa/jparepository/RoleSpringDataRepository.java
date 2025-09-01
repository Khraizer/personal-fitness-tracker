package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository;

import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository interface for RoleJpaEntity.
 * Provides CRUD operations and a custom method to find a role by its name.
 */
public interface RoleSpringDataRepository extends JpaRepository<RoleJpaEntity, Long> {

    /**
     * Finds a Role entity by its role name.
     *
     * @param role the name of the role to search for.
     * @return Optional containing the RoleJpaEntity if found, else empty.
     */
    Optional<RoleJpaEntity> findByRole(String role);
}
