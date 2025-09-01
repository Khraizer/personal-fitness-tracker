package com.khraizer.personalFitnessTracker.application.dto.response;

public record WorkoutLogExerciseResponseDto(Long id, WorkoutLogResponseDto workout, ExerciseResponseDto exercise, int time) { }
