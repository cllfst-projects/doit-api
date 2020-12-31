package com.doit.api.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {

    private String email;
    private String password;

}
