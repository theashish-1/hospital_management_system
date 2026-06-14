

package com.example.database.Config;

import com.example.database.DTO.LoginResponseDTO;
import com.example.database.Service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    @Lazy // This now works because it's a field injection, not a constructor injection
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = token.getPrincipal();

        String registrationId = token.getAuthorizedClientRegistrationId();

        // 3. Get the DTO from your service
        LoginResponseDTO loginResponse = authService.handleoAuth2LoginRequest(oAuth2User, registrationId,response);

        // 4. Manual JSON Write
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // This line will now work because 'objectMapper' is defined
        response.getWriter().write(objectMapper.writeValueAsString(loginResponse));
    }
}
