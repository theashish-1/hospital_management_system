package com.example.database.Service;

import com.example.database.Config.UserDetailsImpl;
import com.example.database.DTO.LoginRequestDTO;
import com.example.database.DTO.LoginResponseDTO;
import com.example.database.DTO.SignupRequestDTO;
import com.example.database.DTO.SignupResponseDTO;
import com.example.database.Entity.User;
import com.example.database.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.database.Entity.type.Role.DOCTOR;
import static com.example.database.Entity.type.Role.PATIENT;

@Service
@AllArgsConstructor

public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public SignupResponseDTO signUp(SignupRequestDTO signupRequestDTO) {
         if(userRepository.findByUsername(signupRequestDTO.getUsername()).isPresent()){
             throw new RuntimeException("User Already found");
         }

         User user = new User();
         user.setUsername(signupRequestDTO.getUsername());
         user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
         user.setRole(signupRequestDTO.getRole());

         User savedUser = userRepository.save(user);

         SignupResponseDTO response = new SignupResponseDTO();
         response.setId(savedUser.getId());
         response.setUsername(savedUser.getUsername());
         response.setRole(savedUser.getRole().name());

         if (savedUser.getPatient() != null ){
             response.setPatientId(savedUser.getPatient().getId());

         }
        if (savedUser.getDoctor() != null ){
            response.setDoctorId(savedUser.getDoctor().getId());

        }

         return response;

    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        System.out.println("loginresponse");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();

            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setUsername(user.getUsername());
            loginResponse.setRole(user.getRole());

            if (user.getPatient() != null) {
                loginResponse.setPatientId(user.getPatient().getId());
            }

            if (user.getDoctor() != null) {
                loginResponse.setDoctorId(user.getDoctor().getId());
            }

            return loginResponse;

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Login failed: " + exception.getMessage());
        }

    }
}
