package com.rserver.miniblog.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TokenBlacklist {

    @Id
    @GeneratedValue
    @Column(name = "token_blacklist_id")
    private Long id;

    @Column(nullable = false, length = 512)
    private String token;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    private TokenBlacklist(String token, Long memberId, LocalDateTime expireAt) {
        this.token = token;
        this.memberId = memberId;
        this.expireAt = expireAt;
    }

    public static TokenBlacklist create(String token, Long memberId, LocalDateTime expireAt) {
        return new TokenBlacklist(token, memberId, expireAt);
    }

}
