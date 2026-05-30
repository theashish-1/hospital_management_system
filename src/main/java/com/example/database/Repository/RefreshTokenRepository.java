package com.example.database.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.database.Entity.RefreshToken;
import com.example.database.Entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,UUID>{
    Optional<RefreshToken> findByjti(String jti);
    void deleteByUser(RefreshToken user);

    void deleteByJti(String jti);
    void deleteByUser(User user);
}
