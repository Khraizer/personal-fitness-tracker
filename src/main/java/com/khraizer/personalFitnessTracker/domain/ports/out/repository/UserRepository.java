package com.khraizer.personalFitnessTracker.domain.ports.out.repository;

import com.khraizer.personalFitnessTracker.domain.model.User;

import java.util.Optional;

/**
 * Output port interface for User repository.
 * Defines operations for managing User persistence.
 */
public interface UserRepository {

    /**
     * Saves a new User.
     */
    void save(User user);

    /**
     * Searches for a User by email.
     */
    Optional<User> search(String email);
}
