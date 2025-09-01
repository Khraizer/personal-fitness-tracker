package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "workout_logs")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutLogJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutJpaEntity workout;
    private LocalDate date;
    private double calories;
    private int time;

    public WorkoutLogJpaEntity(UserJpaEntity user, WorkoutJpaEntity workout, LocalDate date, double calories, int time) {
        this.user = user;
        this.workout = workout;
        this.date = date;
        this.calories = calories;
        this.time = time;
    }
}
