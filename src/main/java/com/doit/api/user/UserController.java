package com.doit.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oUser.get());
    }

    // @PostMapping(value = "/")
    // public ResponseEntity<String> addUser(@RequestBody User user) {
    //     try {
    //         userService.checkUserInput(user);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //     }

    //     User savedUser = userService.createUser(user);
    //     return ResponseEntity.ok(savedUser.getEmail());
    //     // URI userUri = new URI("/users/" + user.getUsername());
    //     // return ResponseEntity.created().build();
    // }

    @GetMapping("/hello")
    public String hello() {
        return "<h1> Hello </h1>";
    }
}