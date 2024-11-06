package org.anastasiiapanchenko.lesson4.validation;

import org.anastasiiapanchenko.lesson4.dto.UserRegistrationDto;

public class UserValidator {
    private static final String EMAIL_PATTERN = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    private static final String PHONE_NUMBER_PATTERN = "^\\+?\\d{10,15}$";

    public void validate(UserRegistrationDto dto) {
        if (dto.getEmail() == null || !dto.getEmail().matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (dto.getPassword() == null || !dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().matches(PHONE_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }
}

