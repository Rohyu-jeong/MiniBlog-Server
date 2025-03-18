package com.rserver.miniblog.infrastructure.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.jar.JarException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, "토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED);
        } catch (JarException e) {
            sendErrorResponse(response, "유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);
        }
    }

    private void sendErrorResponse (HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status.value());
        ErrorResponse errorResponse = ErrorResponse.of(status, message);
        new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
    }

}
