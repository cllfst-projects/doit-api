package com.doit.api.registration;

import com.doit.api.user.User;
import com.doit.api.user.UserRole;
import com.doit.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public Boolean register(RegistrationRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .userRole(UserRole.USER)
                .build();
        return userService.createUser(user);
    }
}
