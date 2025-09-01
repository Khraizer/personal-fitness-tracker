package com.khraizer.personalFitnessTracker.application.dto.request;

import lombok.Builder;

@Builder
public record WorkoutRequestDto(
        Long id,
        String name,
        String description
) {}
