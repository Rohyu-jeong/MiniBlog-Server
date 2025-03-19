package com.rserver.miniblog.application.service.auth;

import com.rserver.miniblog.domain.token.TokenBlacklist;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.infrastructure.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.rserver.miniblog.domain.token.TokenErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    public void add(String token, Long memberId, LocalDateTime expireAt) {
        TokenBlacklist tokenBlacklist = TokenBlacklist.create(token, memberId, expireAt);
        tokenBlacklistRepository.save(tokenBlacklist);
    }

    public void isTokenBlacklisted(String token) {
        if (tokenBlacklistRepository.existsByToken(token)) {
            throw new InvalidTokenException(BLACKLISTED_TOKEN.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void delete() {
        int deleteCount = tokenBlacklistRepository.deleteByExpireAtBefore(LocalDateTime.now());
        log.info("Expired tokens deleted: {}", deleteCount);
    }

}
