package com.khraizer.personalFitnessTracker.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for responding with user data.
 * Contains basic user information including personal details and role.
 */
@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private Double weight;
    private String email;
    private String role;
    private String password;
}
