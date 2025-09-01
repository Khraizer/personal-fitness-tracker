package com.khraizer.personalFitnessTracker.application.handler.input;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.search.WorkoutSearchDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutExerciseResponseDto;

import java.util.List;
import java.util.Optional;

public interface WorkoutExerciseHandlerCli {
    Optional<List<WorkoutExerciseResponseDto>> getAll();
    Optional<List<WorkoutExerciseResponseDto>> findByWorkout(WorkoutSearchDto workout);
    void save(WorkoutExerciseRequestDto dto);
    double calculateCaloriesBurned(double weightKg, int minutes, double met);
}
