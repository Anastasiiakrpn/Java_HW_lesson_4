package org.anastasiiapanchenko.lesson4.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;

    public User(Long id, String email, String phoneNumber, String password) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

}
