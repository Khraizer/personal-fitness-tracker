package com.khraizer.personalFitnessTracker.application.mapper;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutLogExerciseResponseDto;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutLogExercise;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between WorkoutLogExercise domain models and DTOs (request/response).
 */
@Component
public class WorkoutLogExerciseMapperApplication {

    private final WorkoutLogMapperApplication workoutLogMapperApplication;
    private final ExerciseMapperApplication exerciseMapperApplication;

    public WorkoutLogExerciseMapperApplication(WorkoutLogMapperApplication workoutLogMapperApplication,
                                               ExerciseMapperApplication exerciseMapperApplication) {
        this.workoutLogMapperApplication = workoutLogMapperApplication;
        this.exerciseMapperApplication = exerciseMapperApplication;
    }

    /**
     * Converts a request DTO to a domain WorkoutLogExercise model.
     * Delegates nested mapping to WorkoutLogMapperApplication and ExerciseMapperApplication.
     */
    public WorkoutLogExercise toModel(WorkoutLogExerciseRequestDto dto) {
        return new WorkoutLogExercise(
                workoutLogMapperApplication.toModel(dto.workoutLog()),
                exerciseMapperApplication.toModel(dto.workoutExercise()),
                dto.time()
        );
    }

    /**
     * Converts a domain WorkoutLogExercise model to a response DTO.
     * Delegates nested mapping to WorkoutLogMapperApplication and ExerciseMapperApplication.
     */
    public WorkoutLogExerciseResponseDto toDto(WorkoutLogExercise workoutLog) {
        return new WorkoutLogExerciseResponseDto(
                workoutLog.getId(),
                workoutLogMapperApplication.toDto(workoutLog.getWorkoutLog()),
                exerciseMapperApplication.toDto(workoutLog.getWorkoutExercise()),
                workoutLog.getTime()
        );
    }
}
