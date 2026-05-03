package com.example.database.Config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
<<<<<<< HEAD
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
<<<<<<< HEAD
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
=======

public class SecurityConfig {

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf(csrf-> csrf.disable())
<<<<<<< HEAD
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
=======
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/auth/signup","/auth/login").permitAll()
                        .anyRequest().authenticated()

                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // adding jwtAuthFilter before UsernamePasswordAuthenticationFilter ie.e before going to UsernamePasswordAuthenticationFilter it will check  with jwtAuthFilter
<<<<<<< HEAD
                .oauth2Login(oAuth2->oAuth2.failureHandler(
                        (request, response, exception) ->{
                            response.setContentType("text/plain");
                            response.getWriter().write("OAuth Error: " + exception.getMessage());
                        }
                )
                        .successHandler(oAuth2SuccessHandler))
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
                .httpBasic(httpBasic-> httpBasic.disable())
                .formLogin(formLogin-> formLogin.disable());
        return httpSecurity.build();
    }
<<<<<<< HEAD
=======
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider(userDetailsService);

        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38




}
