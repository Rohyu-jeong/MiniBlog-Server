package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.request.LoginRequestDto;
import com.rserver.miniblog.application.dto.request.RefreshTokenRequestDto;
import com.rserver.miniblog.application.dto.response.TokenResponseDto;
import com.rserver.miniblog.application.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login (@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        TokenResponseDto tokenResponseDto = authService.login(requestDto, ipAddress);
        return ResponseEntity.ok(tokenResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout (@Valid @RequestBody RefreshTokenRequestDto requestDto) {
        authService.logout(requestDto.getRefreshToken());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissue (@Valid @RequestBody RefreshTokenRequestDto requestDto) {
        TokenResponseDto tokenResponseDto = authService.reissue(requestDto.getRefreshToken());
        return ResponseEntity.ok(tokenResponseDto);
    }

}
