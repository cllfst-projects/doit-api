package com.doit.api.registration;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String create(@RequestBody RegistrationRequest request) {
        return registionService.register(request);
    }
}
