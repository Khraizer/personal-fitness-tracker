package com.khraizer.personalFitnessTracker.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;
@Setter
@Getter
@Builder
public class WorkoutLog {

    private Long id;
    private User user;
    private Workout workout;
    private LocalDate date;
    private Map<String, Integer> exerciseTime;
    private double calories;
    private int time;

    public WorkoutLog(Long id, User user, Workout workout, LocalDate date, Map<String, Integer> exerciseTime, double calories, int time) {
        this.id = id;
        this.user = user;
        this.workout = workout;
        this.date = date;
        this.exerciseTime = exerciseTime;
        this.calories = calories;
        this.time = time;
    }
}
