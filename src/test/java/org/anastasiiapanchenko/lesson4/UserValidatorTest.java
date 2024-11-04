package org.anastasiiapanchenko.lesson4;

import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;
import org.anastasiiapanchenko.lesson4.validation.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    private final UserValidator validator = new UserValidator();

    @Test
    void testValidUserRegistration() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("kitVasyl@example.com");
        dto.setPhoneNumber("+1234567890");
        dto.setPassword("password");
        dto.setRepeatPassword("password");

        assertDoesNotThrow(() -> validator.validate(dto));
    }

    @Test
    void testPasswordsDoNotMatch() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setPassword("password");
        dto.setRepeatPassword("different");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(dto));
        assertEquals("Passwords do not match", exception.getMessage());
    }

    @Test
    void testInvalidPhoneNumber() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setEmail("kitVasyl@example.com");
        dto.setPassword("password");
        dto.setRepeatPassword("password");
        dto.setPhoneNumber("invalid_phone");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(dto));
        assertEquals("Invalid phone number format", exception.getMessage());
    }
}