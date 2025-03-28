package com.rserver.miniblog.auth.application.service;

import com.rserver.miniblog.auth.application.support.BlacklistAppender;
import com.rserver.miniblog.auth.application.support.RefreshTokenOperator;
import com.rserver.miniblog.auth.application.support.TokenFinder;
import com.rserver.miniblog.auth.presentation.dto.internal.TokenMemberData;
import com.rserver.miniblog.auth.presentation.dto.internal.IssueTokenData;
import com.rserver.miniblog.auth.presentation.dto.AuthToken;
import com.rserver.miniblog.member.application.support.MemberReader;
import com.rserver.miniblog.member.domain.Member;
import com.rserver.miniblog.auth.domain.RefreshToken;
import com.rserver.miniblog.auth.infrastructure.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final MemberReader memberReader;
    private final TokenFinder tokenFinder;
    private final RefreshTokenOperator refreshTokenOperator;
    private final BlacklistAppender blacklistAppender;
    private final JwtProvider jwtProvider;

    public AuthToken issueToken(IssueTokenData issueTokenData) {
        TokenMemberData claims = TokenMemberData.from(issueTokenData.getMember(), issueTokenData.getDeviceInfo(), issueTokenData.getIpAddress());

        String accessToken = jwtProvider.generateAccessToken(claims);
        String refreshToken = jwtProvider.generateRefreshToken(claims.getUsername(), claims.getMemberId());

        refreshTokenOperator.store(claims.getMemberId(), refreshToken, claims.getDeviceInfo(), claims.getIpAddress());

        return AuthToken.of(accessToken, refreshToken);
    }

    public IssueTokenData validateRefreshToken(String refreshToken) {
        tokenFinder.ensureNotBlacklisted(refreshToken);

        RefreshToken token = tokenFinder.findRefreshToken(refreshToken);

        token.validateToken();

        Member member = memberReader.findMember(token.getMemberId());

        return IssueTokenData.from(member, token.getDeviceInfo(), token.getIpAddress());
    }

    public void revokeRefreshToken(String refreshToken) {
        RefreshToken token = tokenFinder.findRefreshToken(refreshToken);
        blacklistAppender.add(token.getRefreshToken(), token.getMemberId(), token.getExpireAt());
        refreshTokenOperator.revoke(token.getRefreshToken());
    }
}
