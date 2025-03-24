package com.rserver.miniblog.auth.application.service;

import com.rserver.miniblog.auth.domain.TokenBlacklist;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.auth.infrastructure.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.rserver.miniblog.auth.domain.TokenErrorMessage.*;

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
            throw new InvalidTokenException(BLACKLISTED_TOKEN);
        }
    }

}
