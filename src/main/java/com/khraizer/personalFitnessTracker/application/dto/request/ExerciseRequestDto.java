package com.khraizer.personalFitnessTracker.application.dto.request;

import lombok.Builder;

@Builder
public record ExerciseRequestDto(
        Long id,
        String name,
        Double met
) {}
