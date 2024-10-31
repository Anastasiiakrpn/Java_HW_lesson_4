package org.anastasiiapanchenko.lesson4.validation;

import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;

public class UserValidator {
    public void validate(UserRegistrationDto dto) {
        if (dto.getEmail() == null || !dto.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().matches("^\\+?\\d{10,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (dto.getPassword() == null || !dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }
}

