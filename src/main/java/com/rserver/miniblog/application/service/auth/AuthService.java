package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.application.dto.internal.IssueTokenInfo;
import com.rserver.miniblog.application.dto.request.LoginRequestDto;
import com.rserver.miniblog.application.dto.AuthToken;
import com.rserver.miniblog.application.dto.response.LoginResponseDto;
import com.rserver.miniblog.infrastructure.security.MemberDetails;
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
    private final AuthTokenManager authTokenManager;

    public AuthToken login(LoginRequestDto requestDto, String ipAddress) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername().toLowerCase(), requestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

        IssueTokenInfo issueTokenInfo = IssueTokenInfo.from(memberDetails.getMember(), requestDto.getDeviceInfo(), ipAddress);

        return authTokenManager.issueToken(issueTokenInfo);
    }

    public AuthToken reissue(String refreshToken) {
        IssueTokenInfo tokenInfo = authTokenManager.validateRefreshToken(refreshToken);
        authTokenManager.revokeRefreshToken(refreshToken);

        IssueTokenInfo issueTokenInfo = IssueTokenInfo.from(tokenInfo.getMember(), tokenInfo.getDeviceInfo(), tokenInfo.getIpAddress());

        return  authTokenManager.issueToken(issueTokenInfo);
    }

    public void logout(String refreshToken) {
        authTokenManager.revokeRefreshToken(refreshToken);
    }

}
