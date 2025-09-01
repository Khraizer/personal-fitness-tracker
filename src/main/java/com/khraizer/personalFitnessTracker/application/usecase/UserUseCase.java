package com.khraizer.personalFitnessTracker.application.usecase;

import com.khraizer.personalFitnessTracker.domain.model.User;
import com.khraizer.personalFitnessTracker.domain.ports.in.UserPortIn;
import com.khraizer.personalFitnessTracker.domain.ports.out.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Use case implementation for User-related operations.
 * Acts as an intermediary between the application layer and the User repository.
 */
@Service
public class UserUseCase implements UserPortIn {

    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a new User to the repository.
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Searches for a User by email.
     * Returns an Optional containing the User if found, or empty otherwise.
     */
    @Override
    public Optional<User> search(String email) {
        return userRepository.search(email).isPresent() ?
                Optional.of(userRepository.search(email).get()) : Optional.empty();
    }
}
