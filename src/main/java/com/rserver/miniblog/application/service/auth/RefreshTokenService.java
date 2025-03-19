package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.domain.token.RefreshToken;
import com.rserver.miniblog.exception.NotFoundException;
import com.rserver.miniblog.infrastructure.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.rserver.miniblog.domain.token.TokenErrorMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public void save(Long memberId, String refreshToken, String deviceInfo, String ipAddress) {
        RefreshToken token = RefreshToken.create(
                memberId,
                refreshToken,
                deviceInfo,
                ipAddress,
                refreshTokenExpiration
        );

        refreshTokenRepository.save(token);
    }

    public RefreshToken find(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                        .orElseThrow(() -> new NotFoundException(TOKEN_NOT_FOUND.getMessage()));
    }

    public void deleteByToken(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    @Scheduled(cron = "0 0 0,12 * * ?")
    public void deleteExpiredTokens() {
        int deleteCount = refreshTokenRepository.deleteByExpireAtBefore(LocalDateTime.now());
        log.info("Expired tokens deleted: {}", deleteCount);
    }

}
