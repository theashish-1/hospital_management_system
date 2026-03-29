package com.example.database.Config;

import com.example.database.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwtSecretKey}")
    private String jwtSecretKey;

    // Use a helper to get the Key object correctly
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Explicitly set algorithm
                .compact();
    }

    public String getUsernameFromToken(String token) {
        // In 0.11.5, the flow is: parserBuilder() -> setSigningKey() -> build() -> parse...
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build() // <--- CRITICAL: You must build the parser first
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}