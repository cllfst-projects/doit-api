package com.doit.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteUserById (@PathVariable long id) {
        return userService.deleteUSer(id);
    }

    @GetMapping(path="/all")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
