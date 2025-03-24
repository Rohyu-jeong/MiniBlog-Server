package com.rserver.miniblog.auth.application.service;

import com.rserver.miniblog.auth.presentation.dto.internal.IssueTokenData;
import com.rserver.miniblog.auth.presentation.dto.request.LoginRequest;
import com.rserver.miniblog.auth.presentation.dto.AuthToken;
import com.rserver.miniblog.auth.infrastructure.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthToken login(LoginRequest requestDto, String ipAddress) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername().toLowerCase(), requestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

        IssueTokenData issueTokenData = IssueTokenData.from(memberDetails.getMember(), requestDto.getDeviceInfo(), ipAddress);

        return tokenService.issueToken(issueTokenData);
    }

    public AuthToken reissue(String refreshToken) {
        IssueTokenData tokenInfo = tokenService.validateRefreshToken(refreshToken);
        tokenService.revokeRefreshToken(refreshToken);

        IssueTokenData issueTokenData = IssueTokenData.from(tokenInfo.getMember(), tokenInfo.getDeviceInfo(), tokenInfo.getIpAddress());

        return  tokenService.issueToken(issueTokenData);
    }

    public void logout(String refreshToken) {
        tokenService.revokeRefreshToken(refreshToken);
    }

}
