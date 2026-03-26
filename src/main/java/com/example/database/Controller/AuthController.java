package com.example.database.Controller;

import com.example.database.DTO.LoginRequestDTO;
import com.example.database.DTO.LoginResponseDTO;
import com.example.database.DTO.SignupRequestDTO;
import com.example.database.DTO.SignupResponseDTO;
import com.example.database.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public  ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
