package com.rserver.miniblog.auth.application.support;

import com.rserver.miniblog.auth.domain.RefreshToken;
import com.rserver.miniblog.auth.infrastructure.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class RefreshTokenOperator {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public void store(Long memberId, String refreshToken, String deviceInfo, String ipAddress) {
        RefreshToken token = RefreshToken.create(
                memberId,
                refreshToken,
                deviceInfo,
                ipAddress,
                refreshTokenExpiration
        );

        refreshTokenRepository.save(token);
    }

    public void revoke(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

}
