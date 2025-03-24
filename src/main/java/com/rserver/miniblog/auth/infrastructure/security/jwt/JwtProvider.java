package com.rserver.miniblog.auth.infrastructure.security.jwt;

import com.rserver.miniblog.auth.presentation.dto.internal.TokenMemberData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    private final PrivateKey privateKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtProvider(
            JwtKeyManager jwtKeyManager,
            @Value("${jwt.access-token-expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration
    ) {
        this.privateKey = jwtKeyManager.getPrivateKey();
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public String generateAccessToken (TokenMemberData claimsData) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plus(accessTokenExpiration, ChronoUnit.MILLIS);

        return Jwts.builder()
                .setSubject(claimsData.getUsername())
                .claim("memberId", claimsData.getMemberId())
                .claim("email", claimsData.getEmail())
                .claim("type", claimsData.getType().name())
                .claim("role", claimsData.getRole().name())
                .claim("deviceInfo", claimsData.getDeviceInfo())
                .claim("ipAddress", claimsData.getIpAddress())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public String generateRefreshToken (String username, long memberId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plus(refreshTokenExpiration, ChronoUnit.MILLIS);

        return Jwts.builder()
                .setSubject(username)
                .claim("memberId", memberId)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

}
