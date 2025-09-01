package com.khraizer.personalFitnessTracker.application.mapper;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.search.WorkoutSearchDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutResponseDto;
import com.khraizer.personalFitnessTracker.domain.model.Workout;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Workout domain models and DTOs (request/response/search).
 */
@Component
public class WorkoutMapperApplication {

    /**
     * Converts a WorkoutRequestDto to a full Workout domain model.
     */
    public Workout toModel(WorkoutRequestDto dto) {
        return Workout.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    /**
     * Converts a WorkoutSearchDto to a partial Workout model (only ID is mapped).
     * Used primarily for lookups or references.
     */
    public Workout toModel(WorkoutSearchDto dto) {
        return Workout.builder()
                .id(dto.id())
                .build();
    }

    /**
     * Converts a domain Workout model to a WorkoutResponseDto.
     */
    public WorkoutResponseDto toDto(Workout workout) {
        return new WorkoutResponseDto(
                workout.id(),
                workout.name(),
                workout.description()
        );
    }
}
