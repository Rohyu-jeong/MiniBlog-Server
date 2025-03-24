package com.rserver.miniblog.auth.infrastructure.repository;

import com.rserver.miniblog.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String RefreshToken);
    int deleteByExpireAtBefore(LocalDateTime now);

}
