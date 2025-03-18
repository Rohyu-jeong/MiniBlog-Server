package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.application.dto.internal.JwtClaims;
import com.rserver.miniblog.application.dto.internal.IssueTokenInfo;
import com.rserver.miniblog.application.dto.AuthToken;
import com.rserver.miniblog.application.service.member.MemberService;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.domain.token.RefreshToken;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.infrastructure.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JwtManager implements AuthTokenManager{

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final BlacklistService blacklistService;
    private final JwtProvider jwtProvider;

    @Override
    public AuthToken issueToken(IssueTokenInfo issueTokenInfo) {
        JwtClaims claims = JwtClaims.from(issueTokenInfo.getMember(), issueTokenInfo.getDeviceInfo(), issueTokenInfo.getIpAddress());

        String accessToken = jwtProvider.generateAccessToken(claims);
        String refreshToken = jwtProvider.generateRefreshToken(claims.getUsername(), claims.getMemberId());

        refreshTokenService.save(claims.getMemberId(), refreshToken, claims.getDeviceInfo(), claims.getIpAddress());

        return AuthToken.of(accessToken, refreshToken);
    }

    @Override
    public IssueTokenInfo validateRefreshToken(String refreshToken) {
        blacklistService.isTokenBlacklisted(refreshToken);

        RefreshToken token = refreshTokenService.find(refreshToken);

        token.validateExpiration();

        Member member = memberService.findMember(token.getMemberId());

        return IssueTokenInfo.from(member, token.getDeviceInfo(), token.getIpAddress());
    }

    @Override
    public void revokeRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenService.find(refreshToken);
        blacklistService.add(token.getRefreshToken(), token.getMemberId(), token.getExpireAt());
        refreshTokenService.deleteByToken(token.getRefreshToken());
    }
}
