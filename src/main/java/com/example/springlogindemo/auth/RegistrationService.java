package com.example.springlogindemo.auth;

import com.example.springlogindemo.controller.RegistrationForm;
import com.example.springlogindemo.model.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public void register (RegistrationForm registrationForm) {

        AppUser newUser = new AppUser();

        newUser.setPassword(registrationForm.getPassword());
        newUser.setFirstName(registrationForm.getFirstName());
        newUser.setLastName(registrationForm.getLastName());
        newUser.setEmail(registrationForm.getEmail());

        appUserService.signUpUser(newUser);
    }
}
