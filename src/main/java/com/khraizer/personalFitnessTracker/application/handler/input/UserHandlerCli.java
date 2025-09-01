package com.khraizer.personalFitnessTracker.application.handler.input;

import com.khraizer.personalFitnessTracker.application.dto.request.UserRequestDto;
import com.khraizer.personalFitnessTracker.application.dto.response.UserResponseDto;

import java.util.Optional;

public interface UserHandlerCli {
    void save(UserRequestDto dto);
    Optional<UserResponseDto> search(String email);
    Optional<UserResponseDto> checkCredentials(String email, String password);
}
