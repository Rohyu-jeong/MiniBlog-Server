package com.rserver.miniblog.auth.presentation.controller;

import com.rserver.miniblog.auth.presentation.dto.request.LoginRequest;
import com.rserver.miniblog.auth.presentation.dto.request.RefreshTokenRequest;
import com.rserver.miniblog.auth.presentation.dto.AuthToken;
import com.rserver.miniblog.common.ApiResponse;
import com.rserver.miniblog.auth.presentation.dto.response.LoginResponse;
import com.rserver.miniblog.auth.application.service.AuthService;
import com.rserver.miniblog.member.application.support.MemberReader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberReader memberReader;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest requestDto, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        AuthToken authToken = authService.login(requestDto, ipAddress);
        String nickname = memberReader.findNickname(requestDto.getUsername());

        LoginResponse responseDto = LoginResponse.of(authToken, nickname);

        return ApiResponse.success(responseDto);
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@Valid @RequestBody RefreshTokenRequest requestDto) {
        authService.logout(requestDto.getRefreshToken());

        return ApiResponse.success();
    }

    @PostMapping("/reissue")
    public ApiResponse<AuthToken> reissue(@Valid @RequestBody RefreshTokenRequest requestDto) {
        AuthToken authToken = authService.reissue(requestDto.getRefreshToken());

        return ApiResponse.success(authToken);
    }

}
