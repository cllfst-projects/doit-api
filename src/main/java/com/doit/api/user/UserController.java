package com.doit.api.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        userService.getAge();
        User user = new User();
        userRepository.save(user);
        return "Hello from spring app";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public int signUp() {

        return 5;
    }
}
