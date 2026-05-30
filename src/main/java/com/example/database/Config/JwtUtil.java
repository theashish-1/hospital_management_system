package com.example.database.Config;

import com.example.database.Entity.User;
import com.example.database.Entity.type.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.DataTruncation;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwtSecretKey}")
    private String jwtSecretKey;

    // Use a helper to get the Key object correctly
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {

        List<String> roles = user.getRoles().stream()
                .map(role -> "ROLE_"+role.name())
                .toList();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId().toString())
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Explicitly set algorithm
                .compact();
    }

    //generate a refresh token 
    public String generateRefreshToken(User user, String jti) {
            Instant now = Instant.now();
            
            long refreshExpirationInSeconds = 30 * 24 * 60 * 60;
            return Jwts.builder()
                    .setId(jti) // This links the JWT to your DB RefreshToken entity's JTI
                    .setSubject(user.getUsername()) // Use username for consistency
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plusSeconds(refreshExpirationInSeconds)))
                    .claim("typ", "Refresh") // Useful for filtering in the JwtAuthFilter
                    .signWith(getSigningKey(), SignatureAlgorithm.HS512)
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

    public List<String> getRolesFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles",List.class);
    }

    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google" -> AuthProviderType.GOOGLE;
            case "github" ->AuthProviderType.GITHUB;
            case "facebook" ->AuthProviderType.FACEBOOK;
            default -> throw new IllegalArgumentException("Unsupported oAuth2 Provider");
        };
    }

    public String determineProviderIdFromOAuth2User(OAuth2User oAuth2User,String registrationId){
        String providerId = switch (registrationId.toLowerCase()){
            case "google"->oAuth2User.getAttribute("sub");
            case "github"->oAuth2User.getAttribute("id").toString();
            default -> {
                log.error("unsupported OAuth2 provider : {}",registrationId);
                throw new IllegalArgumentException("unsupported OAuth2 provider : {}"+registrationId);

            }
        };

        if(providerId == null || providerId.isBlank()){
            log.error("unable to determine providerId for provider  {}",registrationId);
            throw new IllegalArgumentException("unable to determine providerId for OAuth2 login");

        }
        return providerId;
    }

    //sometime we wont get email from oauth provider maybe user did not give access to it  to handel that below logic is used
    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User,String registrationId,String providerId){
        String email = oAuth2User.getAttribute("email");
        if (email!=null && !email.isBlank()){
            return email;
        }
        return switch (registrationId.toLowerCase()){
            case "google"->oAuth2User.getAttribute("sub");
            case "github"->oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }

    
    public String getJtiFromToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(getSigningKey()) // This is why it must be in JwtUtil
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getId(); // This retrieves the 'jti' you set during generation
}
}