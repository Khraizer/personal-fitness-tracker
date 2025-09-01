package com.khraizer.personalFitnessTracker.application.handler.input;

import com.khraizer.personalFitnessTracker.application.dto.request.ExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.ExerciseResponseDto;

import java.util.List;
import java.util.Map;

public interface ExerciseHandlerCli {
    void save(ExerciseRequestDto exercise);
    List<ExerciseResponseDto> getAll();
    Map<Integer,ExerciseResponseDto> findAvailable(WorkoutRequestDto dto);
}
