package com.khraizer.personalFitnessTracker.application.dto.request;

public record WorkoutLogExerciseRequestDto(WorkoutLogRequestDto workoutLog, ExerciseRequestDto workoutExercise, int time) {}

