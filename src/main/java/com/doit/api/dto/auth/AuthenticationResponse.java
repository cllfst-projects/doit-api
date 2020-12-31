package com.doit.api.dto.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private MyUserDetails user;

    public AuthenticationResponse(String jwt ,MyUserDetails user ) {
        this.jwt = jwt;
        this.user = user ;
    }
}
