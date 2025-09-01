package com.khraizer.personalFitnessTracker.application.handler.implementation;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.search.WorkoutSearchDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutExerciseResponseDto;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutExerciseMapperApplication;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutMapperApplication;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutExercisePortIn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CLI implementation for managing workout exercises.
 * Handles saving workout exercises, retrieving exercises by workout,
 * and calculating calories burned.
 */
@Service
public class WorkoutExerciseHandlerCliImpl implements WorkoutExerciseHandlerCli {

    private final WorkoutExercisePortIn workoutExercisePortIn;
    private final WorkoutExerciseMapperApplication mapper;
    private final WorkoutMapperApplication workoutMapperApplication;

    public WorkoutExerciseHandlerCliImpl(WorkoutExercisePortIn workoutExercisePortIn, WorkoutExerciseMapperApplication mapper, WorkoutMapperApplication workoutMapperApplication) {
        this.workoutExercisePortIn = workoutExercisePortIn;
        this.mapper = mapper;
        this.workoutMapperApplication = workoutMapperApplication;
    }

    /**
     * Retrieves all workout exercises.
     * Returns an Optional containing a list of WorkoutExerciseResponseDto,
     * or Optional.empty() if no workout exercises exist.
     */
    @Override
    public Optional<List<WorkoutExerciseResponseDto>> getAll() {
        return workoutExercisePortIn.getAll()
                .map(list -> list.stream()
                        .map(mapper::toDto)
                        .toList());
    }

    /**
     * Finds workout exercises by a given workout.
     * Returns an Optional containing a list of WorkoutExerciseResponseDto,
     * or Optional.empty() if no exercises found for the workout.
     */
    @Override
    public Optional<List<WorkoutExerciseResponseDto>> findByWorkout(WorkoutSearchDto workout) {
        return workoutExercisePortIn.findByWorkout(workoutMapperApplication.toModel(workout))
                .map(list -> list.stream()
                        .map(mapper::toDto)
                        .toList());
    }

    /**
     * Saves a new workout exercise.
     */
    @Override
    public void save(WorkoutExerciseRequestDto dto) {
        workoutExercisePortIn.save(mapper.toModel(dto));
    }

    /**
     * Calculates calories burned based on weight, duration (minutes), and MET value.
     * Formula: calories = MET * weight(kg) * hours.
     */
    @Override
    public double calculateCaloriesBurned(double weightKg, int minutes, double met) {
        double hours = minutes / 60.0;
        return met * weightKg * hours;
    }
}
