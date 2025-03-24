package com.rserver.miniblog.auth.application.support;

import com.rserver.miniblog.auth.domain.TokenBlacklist;
import com.rserver.miniblog.auth.infrastructure.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Transactional
public class BlacklistAppender {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    public void add(String token, Long memberId, LocalDateTime expireAt) {
        TokenBlacklist tokenBlacklist = TokenBlacklist.create(token, memberId, expireAt);
        tokenBlacklistRepository.save(tokenBlacklist);
    }

}
