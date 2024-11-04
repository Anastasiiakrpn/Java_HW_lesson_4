package org.anastasiiapanchenko.lesson4;

import org.anastasiiapanchenko.lesson4.dto.UserResponseDto;
import org.anastasiiapanchenko.lesson4.repository.UserRepository;
import org.anastasiiapanchenko.lesson4.service.UserServiceImpl;
import org.anastasiiapanchenko.lesson4.service.UserService;
import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;
import org.anastasiiapanchenko.lesson4.repository.UserRepositoryJdbcImpl;
import org.anastasiiapanchenko.lesson4.validation.UserValidator;

public final class Main {

    private Main() { }

    public static void main(String[] args) {
        UserRegistrationDto registrationDto = new UserRegistrationDto();
        registrationDto.setEmail("kitVasyl@example.com");
        registrationDto.setPhoneNumber("+1234567890");
        registrationDto.setPassword("password");
        registrationDto.setRepeatPassword("password");

        UserRepository userRepository = new UserRepositoryJdbcImpl();
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserServiceImpl(userRepository, userValidator);

        UserResponseDto userResponseDto = userService.registerUser(registrationDto);

        System.out.println("Registered user ID: " + userResponseDto.id());
        System.out.println("Email: " + userResponseDto.email());
        System.out.println("Phone Number: " + userResponseDto.phoneNumber());
    }
}
