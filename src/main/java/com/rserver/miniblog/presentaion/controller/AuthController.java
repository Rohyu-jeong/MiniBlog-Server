package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.request.LoginRequest;
import com.rserver.miniblog.application.dto.request.RefreshTokenRequest;
import com.rserver.miniblog.application.dto.AuthToken;
import com.rserver.miniblog.application.dto.response.ApiResponse;
import com.rserver.miniblog.application.dto.response.LoginResponse;
import com.rserver.miniblog.application.service.auth.AuthService;
import com.rserver.miniblog.application.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest requestDto, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        AuthToken authToken = authService.login(requestDto, ipAddress);
        String nickname = memberService.findNickname(requestDto.getUsername());

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
