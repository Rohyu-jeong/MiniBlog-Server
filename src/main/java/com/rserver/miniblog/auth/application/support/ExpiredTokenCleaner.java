package com.rserver.miniblog.auth.application.support;

import com.rserver.miniblog.auth.infrastructure.repository.RefreshTokenRepository;
import com.rserver.miniblog.auth.infrastructure.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExpiredTokenCleaner {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Scheduled(cron = "0 0 0,12 * * ?")
    public void cleanRefreshTokens() {
        int deleteCount = refreshTokenRepository.deleteByExpireAtBefore(LocalDateTime.now());
        log.info("Deleted expired refresh tokens: {}", deleteCount);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanBlacklistTokens() {
        int deleteCount = tokenBlacklistRepository.deleteByExpireAtBefore(LocalDateTime.now());
        log.info("Deleted expired blacklist tokens: {}", deleteCount);
    }

}
