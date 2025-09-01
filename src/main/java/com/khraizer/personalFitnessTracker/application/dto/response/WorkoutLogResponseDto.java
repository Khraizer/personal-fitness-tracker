package com.khraizer.personalFitnessTracker.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class WorkoutLogResponseDto {
    private Long id;
    private UserResponseDto user;
    private WorkoutResponseDto workout;
    private LocalDate date;
    private final Map<String, Integer> exerciseTime;
    private double calories;
    private int time;
}
