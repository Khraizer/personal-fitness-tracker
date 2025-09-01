package com.khraizer.personalFitnessTracker.application.dto.request;

import lombok.Builder;

@Builder
public record WorkoutExerciseRequestDto(Long id, WorkoutRequestDto workout, ExerciseRequestDto exercise, int sets, int quantity) {}
