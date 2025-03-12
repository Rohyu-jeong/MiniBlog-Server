package com.rserver.miniblog.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Date;

@Component
public class JwtParser {

    private final PublicKey publicKey;

    public JwtParser (JwtKeyManager jwtKeyManager) {
        this.publicKey = jwtKeyManager.getPublicKey();
    }

    public Claims parseToken (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken (String token) {
        return parseToken(token).getSubject();
    }

    public Long getMemberIdFromToken (String token) {
        return parseToken(token).get("memberId", Long.class);
    }

    public boolean isTokenExpired (String token) {
        try {
            return !parseToken(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

}
