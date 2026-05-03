package com.example.database.Config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.database.Entity.User;
import com.example.database.Entity.type.AuthProviderType;
import com.example.database.Entity.type.Role;
import com.example.database.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AdminInitializer {
    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin@gmail.com").isEmpty()) {
                User admin = User.builder()
                        .username("admin@gmail.com")
                        .password(passwordEncoder.encode("Admin@123"))
                        .roles(Set.of(Role.ADMIN))
                        .providerType(AuthProviderType.EMAIL)
                        .build();
                
                userRepository.save(admin);
                log.info("System Admin created: admin@gmail.com / Admin@123");
            }
        };
    }
}
