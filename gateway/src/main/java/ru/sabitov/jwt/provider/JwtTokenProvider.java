package ru.sabitov.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String jwtSecret;

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            return !isExpired(expirationDate);
        } catch (Exception e) {
            return false;
        }
    }

    public UserInfo getUserDetails(String token) {
        Claims claims = getClaims(token);
        String username = claims.getSubject();
        String roles = claims.get("roles", String.class);

        return new UserInfo(username, roles == null || roles.isBlank() ? List.of() :
                Arrays.stream(roles.split(","))
                        .map(String::trim)
                        .map(SimpleGrantedAuthority::new)
                        .toList());
    }

    private boolean isExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }
}
