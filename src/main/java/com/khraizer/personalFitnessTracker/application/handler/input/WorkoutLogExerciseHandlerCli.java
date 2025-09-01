package com.khraizer.personalFitnessTracker.application.handler.input;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutLogExerciseResponseDto;

import java.util.List;

public interface WorkoutLogExerciseHandlerCli {
    void save(WorkoutLogExerciseRequestDto dto);
    List<WorkoutLogExerciseResponseDto> findByWorkoutLog(WorkoutLogRequestDto workoutLog, UserRequestDto user);
}
