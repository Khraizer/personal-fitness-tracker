package com.khraizer.personalFitnessTracker.application.dto.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record WorkoutLogRequestDto(
        Long id,
        UserRequestDto user,
        WorkoutRequestDto workout,
        LocalDate date,
        Map<String, Integer> exerciseTime,
        double calories,
        int time
) {}
