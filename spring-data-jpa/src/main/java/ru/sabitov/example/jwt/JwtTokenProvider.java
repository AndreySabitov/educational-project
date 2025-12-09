package ru.sabitov.example.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RefreshScope
@Component
public class JwtTokenProvider {
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.secret-key}")
    private String jwtSecret;

    public String generateToken(String username, Collection<? extends GrantedAuthority> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }
}
