package com.khraizer.personalFitnessTracker.infraestructure.output.mapper;

import com.khraizer.personalFitnessTracker.domain.model.Role;
import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.RoleJpaEntity;
import com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User domain models and User JPA entities.
 */
@Component
public class UserMapperInfrastructure {

    /**
     * Converts a JPA entity to a domain model.
     */
    public User toModel(UserJpaEntity user) {
        return new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getWeight(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getRole().equals("Admin") ? Role.Admin : Role.Client
        );
    }

    /**
     * Converts a domain model to a JPA entity.
     */
    public UserJpaEntity toEntity(User user, RoleJpaEntity role) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .weight(user.getWeight())
                .email(user.getEmail())
                .role(role)
                .password(user.getPassword())
                .build();
    }
}
