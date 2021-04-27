package com.doit.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

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
                        new UsernameNotFoundException("user with email " + email + " not found"));
    }

    public String createUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            return("email already exists.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "CREATED";
    }

    public String deleteUSer(long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists){
            return ("User does not exist!");
        }
        userRepository.deleteById(id);
        return ("User deleted!");
    }

    public List<User> getUsers() {
       return userRepository.findAll();
    }
}
