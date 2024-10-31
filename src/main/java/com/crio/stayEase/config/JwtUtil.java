package com.crio.stayEase.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // This should be fetched from a config file or config repo.
    // For simplicity keeping it here.
    private final String SECRET_KEY = "top_secret_key_yek_terces_pot_key_secret_top";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSecurityKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecurityKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecurityKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecurityKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
