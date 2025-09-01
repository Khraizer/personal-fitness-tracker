package com.khraizer.personalFitnessTracker.application.mapper;

import com.khraizer.personalFitnessTracker.application.dto.request.ExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.ExerciseResponseDto;
import com.khraizer.personalFitnessTracker.domain.model.Exercise;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Exercise domain models and DTOs (request/response).
 */
@Component
public class ExerciseMapperApplication {

    /**
     * Converts a domain Exercise model to a response DTO.
     */
    public ExerciseResponseDto toDto(Exercise exercise) {
        return new ExerciseResponseDto(exercise.id(), exercise.name(), exercise.met());
    }

    /**
     * Converts a request DTO to a domain Exercise model.
     */
    public Exercise toModel(ExerciseRequestDto dto) {
        return new Exercise(dto.id(), dto.name(), dto.met());
    }
}
