package com.example.database.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
    private final String refreshCookieName;
    private final boolean cookieHttpOnly;
    private final boolean cookieSecure;
    private final int cookieMaxAge;
    private final String cookieSameSite;
    private final String cookiePath;

    public CookieService(
        @Value("${jwt.refresh.cookie.name:refreshToken}") String refreshCookieName,
        @Value("${jwt.refresh.cookie.http-only:true}") boolean cookieHttpOnly,
        @Value("${jwt.refresh.cookie.secure:false}") boolean cookieSecure,
        @Value("${jwt.refresh.cookie.max-age:604800}") int cookieMaxAge,
        @Value("${jwt.refresh.cookie.same-site:Lax}") String cookieSameSite,
        @Value("${jwt.refresh.cookie.path:/auth/refresh}") String cookiePath
    ) {
        this.refreshCookieName = refreshCookieName;
        this.cookieHttpOnly = cookieHttpOnly;
        this.cookieSecure = cookieSecure;
        this.cookieMaxAge = cookieMaxAge;
        this.cookieSameSite = cookieSameSite;
        this.cookiePath = cookiePath;
    }

    /**
     * Attaches the Refresh Token cookie to the HttpServletResponse.
     */
    public void attachRefreshCookie(HttpServletResponse response, String value) {
        ResponseCookie responseCookie = ResponseCookie.from(refreshCookieName, value)
                .httpOnly(cookieHttpOnly)
                .secure(cookieSecure)
                .maxAge(cookieMaxAge)
                .sameSite(cookieSameSite)
                .path(cookiePath)
                .build();

        // Use addHeader because addCookie only accepts jakarta.servlet.http.Cookie
        // which doesn't support the "SameSite" attribute easily.
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    /**
     * Clears the refresh cookie from the browser (Logout).
     */
    public void clearRefreshCookie(HttpServletResponse response) {
        ResponseCookie responseCookie = ResponseCookie.from(refreshCookieName, "")
                .httpOnly(cookieHttpOnly)
                .secure(cookieSecure)
                .maxAge(0) // 0 tells the browser to delete the cookie
                .sameSite(cookieSameSite)
                .path(cookiePath)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }
}
