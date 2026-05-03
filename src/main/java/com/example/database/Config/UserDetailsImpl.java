package com.example.database.Config;

import com.example.database.Entity.User;
import com.example.database.Entity.type.Role;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private User user;
    public UserDetailsImpl(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(()-> "ROLE_" + role.name());
        });
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }
}
