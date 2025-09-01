package com.khraizer.personalFitnessTracker.application.handler.implementation;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.request.WorkoutLogRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.WorkoutLogResponseDto;
import com.khraizer.personalFitnessTracker.application.handler.input.WorkoutLogHandlerCli;
import com.khraizer.personalFitnessTracker.application.mapper.UserMapperApplication;
import com.khraizer.personalFitnessTracker.application.mapper.WorkoutLogMapperApplication;
import com.khraizer.personalFitnessTracker.domain.ports.in.WorkoutLogPortIn;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * CLI implementation for handling workout log operations.
 * Provides functionality to retrieve, save, update, and find workout logs by user.
 */
@Service
public class WorkoutLogHandlerCliImpl implements WorkoutLogHandlerCli {

    private final WorkoutLogPortIn portIn;
    private final WorkoutLogMapperApplication mapper;
    private final UserMapperApplication userMapperApplication;

    public WorkoutLogHandlerCliImpl(WorkoutLogPortIn portIn, WorkoutLogMapperApplication mapper, UserMapperApplication userMapperApplication) {
        this.portIn = portIn;
        this.mapper = mapper;
        this.userMapperApplication = userMapperApplication;
    }

    /**
     * Retrieves all workout logs.
     * Returns an Optional containing a list of WorkoutLogResponseDto,
     * or Optional.empty() if no logs are found.
     */
    @Override
    public Optional<List<WorkoutLogResponseDto>> getAll() {
        return portIn.getAll().isPresent()
                ? Optional.of(portIn.getAll().get().stream().map(mapper::toDto).toList())
                : Optional.empty();
    }

    /**
     * Saves a new workout log entry.
     * Returns the saved WorkoutLogResponseDto.
     */
    @Override
    public WorkoutLogResponseDto save(WorkoutLogRequestDto workout) {
        return mapper.toDto(portIn.save(mapper.toModel(workout)));
    }

    /**
     * Finds workout logs for a specific user.
     * Returns a map where the key is an index (starting at 1)
     * and the value is the corresponding WorkoutLogResponseDto,
     * sorted in descending order by date.
     */
    @Override
    public Map<Integer, WorkoutLogResponseDto> findByUser(UserRequestDto dto) {
        List<WorkoutLogResponseDto> workoutLogs = portIn.findByUser(userMapperApplication.toModel(dto)).stream()
                .map(mapper::toDto)
                .sorted((a, b) -> b.getDate().compareTo(a.getDate())) // Orden descendente por fecha
                .toList();

        Map<Integer, WorkoutLogResponseDto> workoutLogMap = new LinkedHashMap<>();
        int index = 1;
        for (WorkoutLogResponseDto workoutLog : workoutLogs) {
            workoutLogMap.put(index++, workoutLog);
        }
        return workoutLogMap;
    }

    /**
     * Updates an existing workout log entry.
     */
    @Override
    public void update(WorkoutLogRequestDto workoutLogRequestDto) {
        portIn.update(mapper.toModel(workoutLogRequestDto));
    }
}
