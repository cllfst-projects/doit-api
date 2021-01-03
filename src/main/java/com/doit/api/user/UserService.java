package com.doit.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // TODO remove this
    @PostConstruct
    void init() {
        createUser(User.builder()
                .email("user@abc.com")
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .password("user")
                .build());
        createUser(User.builder()
                .email("admin@abc.com")
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .password("admin")
                .build());
    }

    // this is just an alias for spring security
    public Optional<User> findByUsername(String username) {
        return findByEmail(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	public void checkUserInput(User user) throws IllegalArgumentException {
        throw new IllegalArgumentException("User input not valid");
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
