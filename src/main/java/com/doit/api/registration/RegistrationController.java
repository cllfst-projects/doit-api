package com.doit.api.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registionService;

    @Autowired
    public RegistrationController(RegistrationService registionService) {
        this.registionService = registionService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> create(@RequestBody RegistrationRequest request) {
        if (registionService.register(request)) {
            return new ResponseEntity<>("user CREATED", HttpStatus.OK);
        }
        ;
        return new ResponseEntity<>("email already exists", HttpStatus.CONFLICT);
    }
}
