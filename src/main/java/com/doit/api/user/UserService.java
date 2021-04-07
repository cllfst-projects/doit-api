package com.doit.api.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final UserRepository userRepository;

    public final static String USER_NOT_FOUND_MSG = "user with email %s not found";


    @PostConstruct
    // add some users if db is empty
    void init() {

        createUser(
                new User(
                        "sofiene",
                        "thabet",
                        "sof@gmail.com",
                        "1234",
                        UserRole.USER
                )
        );

        createUser(
                new User(
                        "Ranim",
                        "Naimi",
                        "ranoum@gmail.com",
                        "1234",
                        UserRole.ADMIN
                )

        );
    }

    @Override
    public UserDetails loadUserByUsername(String email ) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)
                        ));
    }

    public String createUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email already exists."); // TODO: need better error handling
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        // TODO: Send confirmation token
        return "CREATED";
    }

}
