package com.khraizer.personalFitnessTracker.application.handler.input;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutLogResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkoutLogHandlerCli {
    Optional<List<WorkoutLogResponseDto>> getAll();
    WorkoutLogResponseDto save(WorkoutLogRequestDto workout);
    Map<Integer, WorkoutLogResponseDto> findByUser(UserRequestDto dto);
    void update(WorkoutLogRequestDto workoutLogRequestDto);
}
