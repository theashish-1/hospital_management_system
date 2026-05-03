package com.example.database.Config;

import com.example.database.Entity.User;
<<<<<<< HEAD
import com.example.database.Entity.type.Role;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.List;
import java.util.Set;
=======
import java.util.List;
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private User user;
    public UserDetailsImpl(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();

    }

<<<<<<< HEAD

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(()-> "ROLE_" + role.name());
        });
        return authorities;
=======
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + user.getRole().name());
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
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
