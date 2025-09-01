package com.khraizer.personalFitnessTracker.application.handler.implementation;

import com.khraizer.personalFitnessTracker.application.dto.request.ExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.ExerciseResponseDto;
import com.khraizer.personalFitnessTracker.application.exception.ExerciseExistsException;
import com.khraizer.personalFitnessTracker.application.handler.input.ExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.mapper.ExerciseMapperApplication;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutMapperApplication;
import com.khraizer.personalFitnessTracker.domain.ports.in.ExercisePortIn;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * CLI implementation of the Exercise handler.
 * Manages exercise registration, retrieval, and filtering available exercises for workouts.
 */
@Service
public class ExerciseHandlerCliImpl implements ExerciseHandlerCli {

    private final ExercisePortIn exercisePortIn;
    private final ExerciseMapperApplication mapper;
    private final WorkoutMapperApplication workoutMapperApplication;

    public ExerciseHandlerCliImpl(ExercisePortIn exercisePortIn, ExerciseMapperApplication mapper, WorkoutMapperApplication workoutMapperApplication) {
        this.exercisePortIn = exercisePortIn;
        this.mapper = mapper;
        this.workoutMapperApplication = workoutMapperApplication;
    }

    /**
     * Saves a new exercise.
     * Throws ExerciseExistsException if an exercise with the same name already exists.
     */
    @Override
    public void save(ExerciseRequestDto exercise) {
        Optional<ExerciseResponseDto> validExercise = findByName(exercise.name());

        if (validExercise.isPresent()) {
            if (exercise.name().equalsIgnoreCase(validExercise.get().name())) {
                throw new ExerciseExistsException("Exercise already exists!");
            }
        }
        exercisePortIn.save(mapper.toModel(exercise));
    }

    /**
     * Retrieves all exercises.
     */
    @Override
    public List<ExerciseResponseDto> getAll() {
        return exercisePortIn.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    /**
     * Finds exercises that are available for a given workout.
     * Returns a map with an index to allow selection by CLI.
     */
    @Override
    public Map<Integer, ExerciseResponseDto> findAvailable(WorkoutRequestDto dto) {
        List<ExerciseResponseDto> exercisesAvailable = exercisePortIn.findAvailable(workoutMapperApplication.toModel(dto))
                .stream()
                .map(mapper::toDto)
                .toList();

        Map<Integer, ExerciseResponseDto> exercises = new HashMap<>();
        int index = 0;
        for (ExerciseResponseDto exerciseResponseDto : exercisesAvailable) {
            exercises.put(index++, exerciseResponseDto);
        }
        return exercises;
    }

    /**
     * Searches for an exercise by name.
     * Returns Optional.empty() if not found.
     */
    private Optional<ExerciseResponseDto> findByName(String name) {
        return exercisePortIn.findByName(name)
                .map(mapper::toDto);
    }
}
