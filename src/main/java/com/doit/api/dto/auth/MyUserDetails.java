package com.doit.api.dto.auth;

import com.doit.api.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class MyUserDetails implements UserDetails {

    private Long user_id;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.user_id = (long) user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
    }

    public MyUserDetails() { }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
