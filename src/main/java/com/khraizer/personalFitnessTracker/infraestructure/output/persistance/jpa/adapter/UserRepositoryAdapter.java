package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.adapter;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.UserRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.mapper.UserMapperInfrastructure;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.UserJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.RoleSpringDataRepository;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.jparepository.UserSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adapter class implementing UserRepository interface.
 * Bridges the domain User model with the JPA persistence layer.
 * Uses UserMapperInfrastructure to convert between domain models and JPA entities.
 * Uses Spring Data repositories to perform database operations.
 */
@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserSpringDataRepository repository;
    private final RoleSpringDataRepository roleRepository;
    private final UserMapperInfrastructure mapper;

    /**
     * Constructor for dependency injection.
     */
    public UserRepositoryAdapter(UserSpringDataRepository repository, RoleSpringDataRepository roleRepository, UserMapperInfrastructure mapper) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    /**
     * Saves a User domain model into the database.
     * Converts the domain model to a JPA entity and assigns the role entity.
     */
    @Override
    public void save(User user) {
        UserJpaEntity userJpaEntity = mapper.toEntity(user, roleRepository.findByRole(user.getRole().name()).get());
        repository.save(userJpaEntity);
    }

    /**
     * Searches for a User by email.
     * Returns an Optional containing the domain model if found, or empty if not.
     */
    @Override
    public Optional<User> search(String email) {
        return repository.findByEmail(email)
                .map(mapper::toModel);
    }
}
