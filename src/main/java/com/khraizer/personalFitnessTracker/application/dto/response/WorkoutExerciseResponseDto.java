package com.khraizer.personalFitnessTracker.application.dto.response;

/**
 * DTO for responding with workout exercise details.
 * Contains the workout info, exercise info, sets, and quantity.
 */
public record WorkoutExerciseResponseDto(
        WorkoutResponseDto workout,
        ExerciseResponseDto exercise,
        int sets,
        int quantity
) {
    @Override
    public String toString() {
        return "WorkoutExerciseResponseDto{" +
                "workout=" + workout.getName() +
                ", exercise=" + exercise.name() +
                ", sets=" + sets +
                ", quantity=" + quantity +
                '}';
    }
}
