package org.anastasiiapanchenko.lesson4;

import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;
import org.anastasiiapanchenko.lesson4.dto.UserResponseDto;
import org.anastasiiapanchenko.lesson4.model.User;
import org.anastasiiapanchenko.lesson4.repository.UserRepository;
import org.anastasiiapanchenko.lesson4.service.UserService;
import org.anastasiiapanchenko.lesson4.service.UserServiceImpl;
import org.anastasiiapanchenko.lesson4.validation.UserValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private UserValidator userValidator;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userValidator = mock(UserValidator.class);
        userService = new UserServiceImpl(userRepository, userValidator);
    }

    @Test
    void testRegisterUser() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail("kitVasyl@example.com");
        userRegistrationDto.setPhoneNumber("+1234567890");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setRepeatPassword("password");

        User user = new User(1L, "kitVasyl@example.com", "+1234567890", "password");
        doNothing().when(userValidator).validate(userRegistrationDto);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDto response = userService.registerUser(userRegistrationDto);
        assertEquals("kitVasyl@example.com", response.email());
        assertEquals("+1234567890", response.phoneNumber());
    }

    @Test
    void testGetUserById() {
        User user = new User(1L, "kitVasyl@example.com", "+1234567890", "password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto response = userService.getUserById(1L);
        assertEquals(1L, response.id());
        assertEquals("kitVasyl@example.com", response.email());
        assertEquals("+1234567890", response.phoneNumber());
    }
}
