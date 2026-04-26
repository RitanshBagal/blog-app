package com.example.blogapp.blogApp.security;

import com.example.blogapp.blogApp.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey key;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;
    private final String issuer;

    public JwtService(@Value("${security.jwt.secret}") String key, @Value("${security.jwt.access-token-expire-time}") long accessTokenExpirationTime, @Value("${security.jwt.refresh-token-expire-time}") long refreshTokenExpirationTime, @Value("${security.jwt.issuer}") String issuer) {
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDto user) {
        Instant now = Instant.now();
        List<String> roles = user.getRole() == null ? List.of() : List.of(user.getRole().toString());
        return Jwts
                .builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getId() + " ")
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(accessTokenExpirationTime)))
                .claims(
                        Map.of("roles", roles, "email", user.getEmail(), "ty", "access")
                )
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(UserDto user) {
        Instant now = Instant.now();
        return Jwts
                .builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getId() + " ")
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(refreshTokenExpirationTime)))
                .claims(
                        Map.of("ty", "refresh")
                )
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isAccessToken(String token) {
        return getPayload(token).get("ty").toString().equals("access");
    }

    public boolean isRefreshToken(String token) {
        return getPayload(token).get("ty").toString().equals("refresh");
    }

    public Jws<Claims> parse(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }

    public Claims getPayload(String token) {
        return this.parse(token).getPayload();
    }

}