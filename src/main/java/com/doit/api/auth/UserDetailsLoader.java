package com.doit.api.auth;

import com.doit.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsLoader implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .map(user -> UserDetails.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .isEnabled(user.isEnabled())
                        .isAccountNonExpired(user.isAccountNonExpired())
                        .isAccountNonLocked(user.isAccountNonLocked())
                        .isCredentialsNonExpired(user.isCredentialsNonExpired())
                        .authorities(List.of()) // TODO
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

}
