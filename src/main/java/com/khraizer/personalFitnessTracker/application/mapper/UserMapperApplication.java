package com.khraizer.personalFitnessTracker.application.mapper;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.UserResponseDto;
import com.khraizer.personalFitnessTracker.domain.model.Role;
import com.khraizer.personalFitnessTracker.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User domain models and DTOs (request/response).
 */
@Component
public class UserMapperApplication {

    /**
     * Converts a UserRequestDto to a domain User model.
     * Sets the role to Client by default.
     */
    public User toModel(UserRequestDto dto) {
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .weight(dto.weight())
                .email(dto.email())
                .password(dto.password())
                .role(Role.Client) // Default role assignment
                .build();
    }

    /**
     * Converts a domain User model to a UserResponseDto.
     */
    public UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getWeight(),
                user.getEmail(),
                user.getRole().name(),
                user.getPassword()
        );
    }
}
