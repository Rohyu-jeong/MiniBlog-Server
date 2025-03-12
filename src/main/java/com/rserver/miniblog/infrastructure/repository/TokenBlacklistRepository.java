package com.rserver.miniblog.infrastructure.repository;

import com.rserver.miniblog.domain.token.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {

    boolean existsByToken(String token);

    int deleteByExpireAtBefore(LocalDateTime now);

}
