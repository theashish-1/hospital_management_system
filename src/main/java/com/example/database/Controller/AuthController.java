package com.example.database.Controller;

import com.example.database.DTO.LoginRequestDTO;
import com.example.database.DTO.LoginResponseDTO;
import com.example.database.DTO.SignupRequestDTO;
import com.example.database.DTO.SignupResponseDTO;
import com.example.database.Service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
         return ResponseEntity.ok(authService.signUp(signupRequestDTO));
    }
    @PostMapping("/login")
    public  ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO
        ,HttpServletResponse response
    ){
        return ResponseEntity.ok(authService.login(loginRequestDTO,response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(
        @CookieValue(name = "refreshToken", required = false) String refreshToken, 
        HttpServletResponse response
    ) {
        if (refreshToken == null) {
            return ResponseEntity.status(400).body(null); // This will return a 400 instead of redirecting to Google
        }
        return ResponseEntity.ok(authService.refreshToken(refreshToken, response));
    }
}
