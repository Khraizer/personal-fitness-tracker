package com.khraizer.personalFitnessTracker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkoutExercise {
    private Long id;
    private Workout workout;
    private Exercise exercise;
    private int sets;
    private int quantity;
}
