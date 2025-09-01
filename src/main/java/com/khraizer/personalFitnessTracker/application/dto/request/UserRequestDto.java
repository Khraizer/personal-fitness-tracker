package com.khraizer.personalFitnessTracker.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * DTO for creating or updating a user.
 * Contains basic required fields with validation annotations.
 */
public record UserRequestDto(
        @NotBlank(message = "The first name can't be empty!")
        String firstName,

        @NotBlank(message = "The last name can't be empty!")
        String lastName,

        Double weight,

        @NotBlank(message = "The email can't be empty!")
        String email,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Password must include at least one lowercase, one uppercase, one digit, and have a minimum length of 8 characters."
        )
        @NotBlank(message = "The password can't be empty!")
        String password
) {}
