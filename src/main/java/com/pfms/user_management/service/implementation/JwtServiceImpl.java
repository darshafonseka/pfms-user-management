package com.pfms.user_management.service.implementation;

import com.pfms.user_management.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
//    private final TokenStore tokenStore;
//    private final String secretKey;
//
//    public JwtServiceImpl(TokenStore tokenStore) {
//        this.tokenStore = tokenStore;
//        this.secretKey = generateSecretKey();
//    }
//
//    @Override
//    public String generateSecretKey() {
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Automatically generates a key of the appropriate size
//        return Base64.getEncoder().encodeToString(key.getEncoded());
//    }
public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Override
    public String generateToken(String username, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }
    private Key getKey() {

        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public void validatingToken(final String token) {
        Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
           return extractExpiration(token).before(new Date());
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
