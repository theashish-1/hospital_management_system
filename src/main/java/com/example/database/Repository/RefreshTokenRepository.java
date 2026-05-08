package com.example.database.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.database.Entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,UUID>{
    
}
