package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workouts_exercises")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkoutExerciseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutJpaEntity workout;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseJpaEntity exercise;
    private int sets;
    private int quantity;
}
