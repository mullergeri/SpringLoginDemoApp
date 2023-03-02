package com.example.springlogindemo.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
