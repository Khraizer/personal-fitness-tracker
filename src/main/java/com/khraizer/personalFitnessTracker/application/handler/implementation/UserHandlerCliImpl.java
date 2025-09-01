package com.khraizer.personalFitnessTracker.application.handler.implementation;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.UserResponseDto;
import com.khraizer.personalFitnessTracker.application.exception.InvalidCredentialsException;
import com.khraizer.personalFitnessTracker.application.exception.UserDoesNotExists;
import com.khraizer.personalFitnessTracker.application.exception.UserExistsException;
import com.khraizer.personalFitnessTracker.application.handler.input.UserHandlerCli;
import com.khraizer.personalFitnessTracker.application.mapper.UserMapperApplication;
import com.khraizer.personalFitnessTracker.domain.model.Role;
import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.ports.in.UserPortIn;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CLI implementation of the user handler.
 * Manages user registration, authentication and search logic for CLI interface.
 */
@Service
public class UserHandlerCliImpl implements UserHandlerCli {

    private final UserPortIn userPortIn;
    private final UserMapperApplication mapper;

    public UserHandlerCliImpl(UserPortIn userPortIn, UserMapperApplication mapper) {
        this.userPortIn = userPortIn;
        this.mapper = mapper;
    }

    /**
     * Registers a new user with default role "Client".
     * Throws exception if the user already exists.
     */
    @Override
    public void save(UserRequestDto dto) throws UserExistsException {
        if (search(dto.email()).isPresent()) {
            throw new UserExistsException(dto.email());
        } else {
            User user = mapper.toModel(dto);
            user.setRole(Role.Client); // Default role
            userPortIn.save(user);
        }
    }

    /**
     * Searches for a user by email.
     */
    @Override
    public Optional<UserResponseDto> search(String email) {
        return userPortIn.search(email)
                .map(mapper::toDto);
    }

    /**
     * Validates user credentials (email and password).
     * Throws exception if credentials are invalid or user is not found.
     */
    @Override
    public Optional<UserResponseDto> checkCredentials(String email, String password) {
        Optional<User> userOpt = userPortIn.search(email);

        if (userOpt.isPresent()) {
            if (password.equals(userOpt.get().getPassword())) {
                return Optional.of(mapper.toDto(userOpt.get()));
            } else {
                throw new InvalidCredentialsException("Invalid Credentials");
            }
        } else {
            throw new UserDoesNotExists(email);
        }
    }
}
