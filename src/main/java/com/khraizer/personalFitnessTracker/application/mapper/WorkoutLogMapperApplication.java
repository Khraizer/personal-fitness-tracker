package com.khraizer.personalFitnessTracker.application.mapper;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutLogResponseDto;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLog;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between WorkoutLog domain models and DTOs (request/response).
 */
@Component
public class WorkoutLogMapperApplication {

    private final UserMapperApplication userMapperApplication;
    private final WorkoutMapperApplication workoutMapperApplication;

    public WorkoutLogMapperApplication(UserMapperApplication userMapperApplication,
                                       WorkoutMapperApplication workoutMapperApplication) {
        this.userMapperApplication = userMapperApplication;
        this.workoutMapperApplication = workoutMapperApplication;
    }

    /**
     * Converts a domain WorkoutLog model to a response DTO.
     * Includes nested mappings for user and workout.
     */
    public WorkoutLogResponseDto toDto(WorkoutLog workout) {
        return new WorkoutLogResponseDto(
                workout.getId(),
                userMapperApplication.toDto(workout.getUser()),
                workoutMapperApplication.toDto(workout.getWorkout()),
                workout.getDate(),
                workout.getExerciseTime(),
                workout.getCalories(),
                workout.getTime()
        );
    }

    /**
     * Converts a request DTO to a domain WorkoutLog model.
     * Handles null checks for nested user and workout mappings.
     */
    public WorkoutLog toModel(WorkoutLogRequestDto dto) {
        return WorkoutLog.builder()
                .id(dto.id())
                .user(dto.user() != null ? userMapperApplication.toModel(dto.user()) : null)
                .workout(dto.workout() != null ? workoutMapperApplication.toModel(dto.workout()) : null)
                .date(dto.date())
                .calories(dto.calories())
                .time(dto.time())
                .build();
    }
}
