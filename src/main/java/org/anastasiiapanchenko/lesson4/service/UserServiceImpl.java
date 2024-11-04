package org.anastasiiapanchenko.lesson4.service;

import org.anastasiiapanchenko.lesson4.model.User;
import org.anastasiiapanchenko.lesson4.dto.UserResponseDto;
import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;
import org.anastasiiapanchenko.lesson4.repository.UserRepository;
import org.anastasiiapanchenko.lesson4.validation.UserValidator;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationDto userRegistrationDto) {
        userValidator.validate(userRegistrationDto);
        User user = new User(null, userRegistrationDto.getEmail(), userRegistrationDto.getPhoneNumber(), userRegistrationDto.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPhoneNumber());
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserResponseDto(user.getId(), user.getEmail(), user.getPhoneNumber()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
