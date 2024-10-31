package org.anastasiiapanchenko.lesson4.service;

import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;
import org.anastasiiapanchenko.lesson4.dto.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);

    UserResponseDto getUserById(Long userId);
}
