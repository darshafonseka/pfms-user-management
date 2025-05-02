package com.pfms.user_management.service;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
//    String generateSecretKey();
    String generateToken(String username, String role);
    String extractUsername(String token);
    String extractRole(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    boolean validateToken(String token, UserDetails userDetails);
    void validatingToken( String token);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
}

