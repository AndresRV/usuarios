package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private Integer documentNumber;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String pass;
}
