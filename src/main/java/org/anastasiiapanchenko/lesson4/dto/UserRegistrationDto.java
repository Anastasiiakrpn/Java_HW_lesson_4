package org.anastasiiapanchenko.lesson4.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDto {
    private String email;
    private String phoneNumber;
    private String password;
    private String repeatPassword;

}
