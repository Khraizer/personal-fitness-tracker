package com.khraizer.personalFitnessTracker.domain.model;

public class WorkoutLogExercise {
    private Long id;
    private WorkoutLog workoutLog;
    private Exercise workoutExercise;
    private int time;

    public WorkoutLogExercise(WorkoutLog workoutLog, Exercise workoutExercise, int time) {
        this.workoutLog = workoutLog;
        this.workoutExercise = workoutExercise;
        this.time = time;
    }

    public WorkoutLogExercise(Long id, WorkoutLog workoutLog, Exercise workoutExercise, int time) {
        this.id = id;
        this.workoutLog = workoutLog;
        this.workoutExercise = workoutExercise;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkoutLog getWorkoutLog() {
        return workoutLog;
    }

    public void setWorkoutLog(WorkoutLog workoutLog) {
        this.workoutLog = workoutLog;
    }

    public Exercise getWorkoutExercise() {
        return workoutExercise;
    }

    public void setWorkoutExercise(Exercise workoutExercise) {
        this.workoutExercise = workoutExercise;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
