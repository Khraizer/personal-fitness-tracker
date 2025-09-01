package com.khraizer.personalFitnessTracker.application.handler.implementation;

import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutResponseDto;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutHandlerCli;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutMapperApplication;
import com.khraizer.personalFitnessTracker.domain.model.Workout;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutPortIn;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CLI implementation for handling workout-related operations.
 * Supports retrieving all workouts, searching by name, and saving new workouts.
 */
@Service
public class WorkoutHandlerCliImpl implements WorkoutHandlerCli {

    private final WorkoutPortIn workoutPortIn;
    private final WorkoutMapperApplication mapper;

    public WorkoutHandlerCliImpl(WorkoutPortIn workoutPortIn, WorkoutMapperApplication mapper) {
        this.workoutPortIn = workoutPortIn;
        this.mapper = mapper;
    }

    /**
     * Retrieves all workouts from the data source.
     * Returns an Optional containing a map where the key is an index (starting at 1)
     * and the value is the corresponding WorkoutResponseDto.
     * Returns Optional.empty() if no workouts are found.
     */
    @Override
    public Optional<Map<Integer, WorkoutResponseDto>> getAll() {
        if (workoutPortIn.getAll().isPresent()) {
            Map<Integer, WorkoutResponseDto> workoutMap = new HashMap<>();
            int index = 1;
            for (Workout workout : workoutPortIn.getAll().get()) {
                workoutMap.put(index++, mapper.toDto(workout));
            }
            return Optional.of(workoutMap);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Searches for a workout by its name.
     * Returns an Optional containing the WorkoutResponseDto if found,
     * or Optional.empty() if not found.
     */
    @Override
    public Optional<WorkoutResponseDto> findByName(String name) {
        return workoutPortIn.findByName(name)
                .map(workout -> mapper.toDto(workout));
    }

    /**
     * Saves a new workout to the data source.
     */
    @Override
    public void save(WorkoutRequestDto workoutRequestDto) {
        workoutPortIn.save(mapper.toModel(workoutRequestDto));
    }
}
