package com.doit.api.auth;

import com.doit.api.user.User;
import com.doit.api.user.UserService;
import com.doit.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/")
    public ResponseEntity<HelloResponse> hello() {
        return ResponseEntity.ok(new HelloResponse("Hello, World!"));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String dummyToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(dummyToken)
                .build();
        return ResponseEntity.ok(response);

//         try {
//             Authentication a = authenticationManager.authenticate(
//                     new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
//         } catch (BadCredentialsException e) {
//             throw new Exception("Incorrect email or password", e);
//         }
//         final UserDetails userDetails = (UserDetails) userDetailsLoader.loadUserByUsername(authenticationRequest.getEmail());
//         final String jwt = jwtTokenUtil.generateToken(userDetails);
//         return ResponseEntity.ok(new AuthenticationResponse(jwt , userDetails));*/
    }

}
