package com.khraizer.personalFitnessTracker.application.handler.input;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutResponseDto;

import java.util.Map;
import java.util.Optional;

public interface WorkoutHandlerCli {
    Optional<Map<Integer, WorkoutResponseDto>> getAll();
    Optional<WorkoutResponseDto> findByName(String name);
    void save(WorkoutRequestDto workoutRequestDto);
}
