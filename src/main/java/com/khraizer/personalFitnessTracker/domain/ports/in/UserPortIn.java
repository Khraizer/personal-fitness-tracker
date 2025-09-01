package com.khraizer.personalFitnessTracker.domain.ports.in;

import com.khraizer.personalFitnessTracker.domain.model.User;

import java.util.Optional;

/**
 * Input port interface for User use cases.
 * Defines operations related to User management.
 */
public interface UserPortIn {

    /**
     * Saves a new User or updates an existing one.
     */
    void save(User user);

    /**
     * Searches for a User by email.
     * Returns an Optional containing the User if found.
     */
    Optional<User> search(String email);
}
