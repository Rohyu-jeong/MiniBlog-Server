package com.rserver.miniblog.auth.application.support;

import com.rserver.miniblog.auth.domain.RefreshToken;
import com.rserver.miniblog.auth.infrastructure.repository.RefreshTokenRepository;
import com.rserver.miniblog.auth.infrastructure.repository.TokenBlacklistRepository;
import com.rserver.miniblog.exception.InvalidTokenException;
import com.rserver.miniblog.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.rserver.miniblog.auth.domain.TokenErrorMessage.BLACKLISTED_TOKEN;
import static com.rserver.miniblog.auth.domain.TokenErrorMessage.TOKEN_NOT_FOUND;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenFinder {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenBlacklistRepository tokenBlacklistRepository;

    public RefreshToken findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(TOKEN_NOT_FOUND));
    }

    public void ensureNotBlacklisted(String token) {
        if (tokenBlacklistRepository.existsByToken(token)) {
            throw new InvalidTokenException(BLACKLISTED_TOKEN);
        }
    }

}
