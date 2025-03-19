package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.application.dto.internal.TokenMemberData;
import com.rserver.miniblog.application.dto.internal.IssueTokenData;
import com.rserver.miniblog.application.dto.AuthToken;
import com.rserver.miniblog.application.service.member.MemberService;
import com.rserver.miniblog.domain.member.Member;
import com.rserver.miniblog.domain.token.RefreshToken;
import com.rserver.miniblog.infrastructure.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtManager implements AuthTokenManager{

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final BlacklistService blacklistService;
    private final JwtProvider jwtProvider;

    @Override
    public AuthToken issueToken(IssueTokenData issueTokenData) {
        TokenMemberData claims = TokenMemberData.from(issueTokenData.getMember(), issueTokenData.getDeviceInfo(), issueTokenData.getIpAddress());

        String accessToken = jwtProvider.generateAccessToken(claims);
        String refreshToken = jwtProvider.generateRefreshToken(claims.getUsername(), claims.getMemberId());

        refreshTokenService.save(claims.getMemberId(), refreshToken, claims.getDeviceInfo(), claims.getIpAddress());

        return AuthToken.of(accessToken, refreshToken);
    }

    @Override
    public IssueTokenData validateRefreshToken(String refreshToken) {
        blacklistService.isTokenBlacklisted(refreshToken);

        RefreshToken token = refreshTokenService.find(refreshToken);

        token.validateExpiration();

        Member member = memberService.findMember(token.getMemberId());

        return IssueTokenData.from(member, token.getDeviceInfo(), token.getIpAddress());
    }

    @Override
    public void revokeRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenService.find(refreshToken);
        blacklistService.add(token.getRefreshToken(), token.getMemberId(), token.getExpireAt());
        refreshTokenService.deleteByToken(token.getRefreshToken());
    }
}
