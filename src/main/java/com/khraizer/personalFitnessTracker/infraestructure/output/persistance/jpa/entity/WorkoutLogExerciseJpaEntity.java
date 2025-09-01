package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workout_logs_exercises")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutLogExerciseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "workout_log_id")
    private WorkoutLogJpaEntity workoutLog;
    @ManyToOne
    @JoinColumn(name = "workout_exercise_id")
    private ExerciseJpaEntity workoutExercise;
    private int time;

    public WorkoutLogExerciseJpaEntity(WorkoutLogJpaEntity workoutLog, ExerciseJpaEntity workoutExercise, int time) {
        this.workoutLog = workoutLog;
        this.workoutExercise = workoutExercise;
        this.time = time;
    }
}
