package com.khraizer.personalFitnessTracker.application.dto.response;

/**
 * DTO for responding with exercise data.
 * Contains basic exercise information like id, name, and MET value.
 */
public record ExerciseResponseDto(
        Long id,
        String name,
        Double met
) {
    @Override
    public String toString() {
        return name;
    }
}
