package com.example.springlogindemo.auth;


import com.example.springlogindemo.model.AppUser;
import com.example.springlogindemo.model.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(() ->
                    new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

        public void signUpUser (AppUser appUser) {

            String encodedPassword = new BCryptPasswordEncoder().encode(appUser.getPassword());

            appUser.setPassword(encodedPassword);
            appUser.setEnabled(true);
            appUser.setLocked(false);

            appUserRepository.save(appUser);

        }
}
