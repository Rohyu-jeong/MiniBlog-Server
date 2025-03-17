package com.rserver.miniblog.presentaion.controller;

import com.rserver.miniblog.application.dto.internal.NicknameInfo;
import com.rserver.miniblog.application.dto.request.LoginRequestDto;
import com.rserver.miniblog.application.dto.request.RefreshTokenRequestDto;
import com.rserver.miniblog.application.dto.AuthToken;
import com.rserver.miniblog.application.dto.response.LoginResponseDto;
import com.rserver.miniblog.application.service.auth.AuthService;
import com.rserver.miniblog.application.service.member.MemberService;
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
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        AuthToken authToken = authService.login(requestDto, ipAddress);
        NicknameInfo nicknameInfo = memberService.getNicknameInfo(requestDto.getUsername());

        LoginResponseDto responseDto = LoginResponseDto.of(authToken, nicknameInfo);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequestDto requestDto) {
        authService.logout(requestDto.getRefreshToken());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<AuthToken> reissue(@Valid @RequestBody RefreshTokenRequestDto requestDto) {
        AuthToken authToken = authService.reissue(requestDto.getRefreshToken());
        return ResponseEntity.ok(authToken);
    }

}
