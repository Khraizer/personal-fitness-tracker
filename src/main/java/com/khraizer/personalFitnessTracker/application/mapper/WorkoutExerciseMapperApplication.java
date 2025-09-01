package com.khraizer.personalFitnessTracker.application.mapper;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutExerciseResponseDto;
import com.khraizer.personalFitnessTracker.domain.model.WorkoutExercise;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between WorkoutExercise domain models and DTOs (request/response).
 */
@Component
public class WorkoutExerciseMapperApplication {

    private final WorkoutMapperApplication workoutMapperApplication;
    private final ExerciseMapperApplication exerciseMapperApplication;

    public WorkoutExerciseMapperApplication(WorkoutMapperApplication workoutMapperApplication,
                                            ExerciseMapperApplication exerciseMapperApplication) {
        this.workoutMapperApplication = workoutMapperApplication;
        this.exerciseMapperApplication = exerciseMapperApplication;
    }

    /**
     * Converts a domain WorkoutExercise model to a response DTO.
     * Delegates nested mapping to WorkoutMapperApplication and ExerciseMapperApplication.
     */
    public WorkoutExerciseResponseDto toDto(WorkoutExercise model) {
        return new WorkoutExerciseResponseDto(
                workoutMapperApplication.toDto(model.getWorkout()),
                exerciseMapperApplication.toDto(model.getExercise()),
                model.getSets(),
                model.getQuantity()
        );
    }

    /**
     * Converts a request DTO to a domain WorkoutExercise model.
     * Delegates nested mapping to WorkoutMapperApplication and ExerciseMapperApplication.
     */
    public WorkoutExercise toModel(WorkoutExerciseRequestDto dto) {
        return new WorkoutExercise(
                dto.id(),
                workoutMapperApplication.toModel(dto.workout()),
                exerciseMapperApplication.toModel(dto.exercise()),
                dto.sets(),
                dto.quantity()
        );
    }
}
