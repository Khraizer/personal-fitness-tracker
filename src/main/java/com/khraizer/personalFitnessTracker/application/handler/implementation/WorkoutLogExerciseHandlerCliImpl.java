package com.khraizer.personalFitnessTracker.application.handler.implementation;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogExerciseRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutLogExerciseResponseDto;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutLogExerciseHandlerCli;
import com.khraizer.personalFitnessTracker.application.mapper.UserMapperApplication;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutLogExerciseMapperApplication;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutLogMapperApplication;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutLogExercisePortIn;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CLI implementation for handling workout log exercises.
 * Responsible for saving and retrieving logged exercises for a given workout.
 */
@Service
public class WorkoutLogExerciseHandlerCliImpl implements WorkoutLogExerciseHandlerCli {

    private final WorkoutLogExercisePortIn portIn;
    private final WorkoutLogExerciseMapperApplication mapper;
    private final WorkoutLogMapperApplication workoutLogMapperApplication;
    private final UserMapperApplication userMapperApplication;

    public WorkoutLogExerciseHandlerCliImpl(
            WorkoutLogExercisePortIn portIn,
            WorkoutLogExerciseMapperApplication mapper,
            WorkoutLogMapperApplication workoutLogMapperApplication,
            UserMapperApplication userMapperApplication
    ) {
        this.portIn = portIn;
        this.mapper = mapper;
        this.workoutLogMapperApplication = workoutLogMapperApplication;
        this.userMapperApplication = userMapperApplication;
    }

    /**
     * Saves a workout log exercise.
     *
     * @param dto the workout log exercise to be saved
     */
    @Override
    public void save(WorkoutLogExerciseRequestDto dto) {
        portIn.save(mapper.toModel(dto));
    }

    /**
     * Finds all exercises logged for a specific workout and user.
     *
     * @param workoutLog the workout log
     * @param user the user who logged the workout
     * @return a list of logged exercises
     */
    @Override
    public List<WorkoutLogExerciseResponseDto> findByWorkoutLog(WorkoutLogRequestDto workoutLog, UserRequestDto user) {
        return portIn.findByWorkoutLog(
                        workoutLogMapperApplication.toModel(workoutLog),
                        userMapperApplication.toModel(user)
                ).get().stream()
                .map(mapper::toDto)
                .toList();
    }
}
