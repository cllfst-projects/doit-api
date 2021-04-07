package com.doit.api.registration;

import com.doit.api.user.User;
import com.doit.api.user.UserRole;
import com.doit.api.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserService userService;

    public String register(RegistrationRequest request) {
        return userService.createUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}
