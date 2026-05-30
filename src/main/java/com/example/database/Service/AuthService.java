//package com.example.database.Service;
//
//import com.example.database.Config.JwtUtil;
//import com.example.database.Config.UserDetailsImpl;
//import com.example.database.DTO.LoginRequestDTO;
//import com.example.database.DTO.LoginResponseDTO;
//import com.example.database.DTO.SignupRequestDTO;
//import com.example.database.DTO.SignupResponseDTO;
//import com.example.database.Entity.User;
//import com.example.database.Entity.type.AuthProviderType;
//import com.example.database.Repository.UserRepository;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import static com.example.database.Entity.type.Role.DOCTOR;
//import static com.example.database.Entity.type.Role.PATIENT;
//
//@Service
//@AllArgsConstructor
//
//public class AuthService {
//
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtUtil jwtUtil;
//
//    //below is nornal sign in using controller
//    public SignupResponseDTO signUp(SignupRequestDTO signupRequestDTO) {
////         if(userRepository.findByUsername(signupRequestDTO.getUsername()).isPresent()){
////             throw new RuntimeException("User Already found");
////         }
////
////         User user = new User();
////         user.setUsername(signupRequestDTO.getUsername());
////         user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
////         user.setRole(signupRequestDTO.getRole());
////
////         User savedUser = userRepository.save(user);
//
//        User user = signup(signupRequestDTO,AuthProviderType.EMAIL,null);
//
//         SignupResponseDTO response = new SignupResponseDTO();
//         response.setId(savedUser.getId());
//         response.setUsername(savedUser.getUsername());
//         response.setRole(savedUser.getRole().name());
//
//         if (savedUser.getPatient() != null ){
//             response.setPatientId(savedUser.getPatient().getId());
//
//         }
//        if (savedUser.getDoctor() != null ){
//            response.setDoctorId(savedUser.getDoctor().getId());
//
//        }
//
//         return response;
//
//    }
//
//    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
////        System.out.println("loginresponse");
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequestDTO.getUsername(),
//                            loginRequestDTO.getPassword()
//                    )
//            );
//
//            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//            User user = userDetails.getUser();
//
//            LoginResponseDTO loginResponse = new LoginResponseDTO();
//            loginResponse.setUsername(user.getUsername());
//            loginResponse.setRole(user.getRole());
//            loginResponse.setUserId(user.getId());
//
//            if (user.getPatient() != null) {
//                loginResponse.setPatientId(user.getPatient().getId());
//            }
//
//            if (user.getDoctor() != null) {
//                loginResponse.setDoctorId(user.getDoctor().getId());
//            }
//            String token = jwtUtil.generateAccessToken(user);
//            loginResponse.setToken(token);
//
//            return loginResponse;
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            throw new RuntimeException("Login failed: " + exception.getMessage());
//        }
//
//    }
//    public User signup(LoginRequestDTO signupRequestDTO,AuthProviderType authProviderType,String providerId){
//        User user = userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);
//        if (user !=null) throw new IllegalArgumentException("User already exist");
//
//        user =  userRepository.save(User.builder()
//                .username(signupRequestDTO.getUsername())
//                        .providerType(authProviderType)
//                        .providerId(providerId)
//                        .build()
//        );
//        if (authProviderType == authProviderType.EMAIL){
//            user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
//        }
//        return userRepository.save(user);
//
//
//    }
//
//    @Transactional
//    public ResponseEntity<LoginResponseDTO> handleoAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
//        //we have to fetch provideType(google,github) and its providerId
//        //save the provider type and provider id  info with user because user assume user looged in with github one and then again with goolge and here user will have dual that has to be avoided
//        //check if user already has an account if present direct login if not first do signup and then login
//
//        AuthProviderType providerType = jwtUtil.getProviderTypeFromRegistrationId(registrationId);
//        String providerId = jwtUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);
//
//        User user = userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);
//
//        String email = oAuth2User.getAttribute("email");
//
//        User emailUser = userRepository.findByUsername(email).orElse(null);
//
//
//        if(user == null && emailUser == null){
//            //user does not exist
//            //go to signup
//            String username = jwtUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId,providerId);
//            SignupRequestDTO signupRequestDTO = signUp(new LoginRequestDTO(username,null,providerId));
//
//        } else if (user!=null) {
//            if (email!=null && !email.isBlank() && !email.equals(user.getUsername())){
//                user.setUsername(email);
//                userRepository.save(user);
//            }
//        }else{
//            throw new BadCredentialsException("this is email already registered with provider"+emailUser);
//        }
//
//        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(jwtUtil.generateAccessToken(user,user.getId()));
//        return ResponseEntity.ok(loginResponseDTO);
//
//    }
//}















package com.example.database.Service;

import com.example.database.Config.JwtUtil;
import com.example.database.Config.UserDetailsImpl;
import com.example.database.DTO.*;
import com.example.database.Entity.Doctor;
import com.example.database.Entity.Patient;
import com.example.database.Entity.RefreshToken;
import com.example.database.Entity.User;
import com.example.database.Entity.type.AuthProviderType;
import com.example.database.Entity.type.Role;
import com.example.database.Repository.RefreshTokenRepository;
import com.example.database.Repository.UserRepository;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;


    /**
     * 1. NORMAL SIGNUP (via Form/Postman)
     */
    @Transactional
    public SignupResponseDTO signUp(SignupRequestDTO request) {
        System.out.println("inside signup service");
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
//        Set<Role> roles = new HashSet<>();
//        roles.add(request.getRoles() != null ? request.getRoles() : Role.PATIENT);

        Set<Role> assignedRoles = request.getRoles();
        if (assignedRoles == null || assignedRoles.isEmpty()) {
            assignedRoles = Set.of(Role.PATIENT);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(assignedRoles)
                .providerType(AuthProviderType.EMAIL) //telling its a nornal credential based login This user belongs to the local database category
                .build();

        if (user.getRoles().contains(Role.PATIENT)){
            Patient patient = new Patient();
            patient.setName(request.getName()); // Populating data from the DTO
            patient.setUser(user);
            user.setPatient(patient);
        }

        if (user.getRoles().contains(Role.DOCTOR)){
            Doctor doctor = new Doctor();
            doctor.setName(request.getName());
            doctor.setUser(user);
            user.setDoctor(doctor);
        }

        User savedUser = userRepository.save(user);
        return mapToSignupResponse(savedUser);
    }

    /**
     * 2. NORMAL LOGIN (via Form/Postman)
     */
    public LoginResponseDTO login(LoginRequestDTO request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return mapToLoginResponse(userDetails.getUser(),response);
    }

    /**
     * 3. OAUTH2 LOGIN/SIGNUP (Google/GitHub)
     */
    @Transactional
    public LoginResponseDTO handleoAuth2LoginRequest(OAuth2User oAuth2User, String registrationId,HttpServletResponse response) {
        AuthProviderType providerType = jwtUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = jwtUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);
        String email = jwtUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);

        // Logic: Find user by ProviderID OR by Email
        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType)
                .orElseGet(() -> {
                    // Check if email is already registered via EMAIL or another provider
                    userRepository.findByUsername(email).ifPresent(u -> {
                        throw new BadCredentialsException("Email " + email + " is already registered via " + u.getProviderType());
                    });
                    Set<Role> defaultRoles = new HashSet<>();
                    defaultRoles.add(Role.PATIENT);

                    // Create new OAuth User
                    log.info("Creating new OAuth user for email: {}", email);
                    return userRepository.save(User.builder()
                            .username(email)
                            .roles(defaultRoles) // Default role for OAuth users
                            .providerType(providerType)
                            .providerId(providerId)
                            .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                            .build());
                });

        return mapToLoginResponse(user,response);
    }


    private LoginResponseDTO mapToLoginResponse(User user,HttpServletResponse response) {
        String token = jwtUtil.generateAccessToken(user);

        String jti = UUID.randomUUID().toString();
        var refreshToken = RefreshToken.builder()
            .jti(jti)
            .user(user)
            .createdAt(Instant.now())
            .expiresAt(Instant.now().plusMillis(30 * 24 * 60 * 60))
            .revoked(false)
            .build();
        refreshTokenRepository.save(refreshToken);

        String refreshTokenString = jwtUtil.generateRefreshToken(user, jti);

        //use cookie service to attach refresh token in cookie 
        cookieService.attachRefreshCookie(response, refreshTokenString);
        

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setUserId(user.getId());
        responseDTO.setUsername(user.getUsername());

        // Since user has many roles, you might want to return the whole set
        // or just the first one if your DTO still expects a single Role.
        if (!user.getRoles().isEmpty()) {
            responseDTO.setRole(user.getRoles().iterator().next());
        }
        responseDTO.setRefreshToken(refreshTokenString);
        responseDTO.setToken(token);
        responseDTO.setProviderId(user.getProviderId());

        if (user.getPatient() != null) responseDTO.setPatientId(user.getPatient().getId());
        if (user.getDoctor() != null) responseDTO.setDoctorId(user.getDoctor().getId());

        return responseDTO;
    }

    @Transactional
    public LoginResponseDTO refreshToken(String refreshTokenString, HttpServletResponse response) {
        // 1. Extract the JTI from the JWT string to find it in our DB

        String jti = jwtUtil.getJtiFromToken(refreshTokenString);

        // 2. Look up the token in the database
        RefreshToken oldToken = refreshTokenRepository.findByjti(jti)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        // 3. Security Checks
        if (oldToken.isRevoked()) {
            // If someone tries to use a revoked token, logout all sessions for safety
            refreshTokenRepository.deleteByUser(oldToken.getUser());
            throw new RuntimeException("Token has been revoked! Potential theft detected.");
        }

        if (oldToken.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(oldToken);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }

        // 4. Mark old token as used/revoked (Rotation)
        oldToken.setRevoked(true);
        refreshTokenRepository.save(oldToken);

        // 5. Generate a new pair using your existing mapper
        // This will generate a NEW JTI, a NEW Cookie, and a NEW Access Token
        return mapToLoginResponse(oldToken.getUser(), response);
    }

    
    private SignupResponseDTO mapToSignupResponse(User user) {
        SignupResponseDTO response = new SignupResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());

        // Join roles into a string for the DTO
        String rolesString = user.getRoles().stream()
                .map(Enum::name)
                .reduce((a, b) -> a + "," + b)
                .orElse("");
        response.setRole(rolesString);

        return response;
    }
}
