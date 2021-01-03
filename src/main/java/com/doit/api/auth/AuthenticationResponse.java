package com.doit.api.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private UserDetails user;

    public AuthenticationResponse(String jwt, UserDetails user ) {
        this.jwt = jwt;
        this.user = user ;
    }
}
