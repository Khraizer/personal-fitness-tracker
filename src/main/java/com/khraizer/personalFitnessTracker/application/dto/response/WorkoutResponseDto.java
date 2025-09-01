package com.khraizer.personalFitnessTracker.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkoutResponseDto {
    private Long id;
    private String name;
    private String description;

    public WorkoutResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public WorkoutResponseDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
