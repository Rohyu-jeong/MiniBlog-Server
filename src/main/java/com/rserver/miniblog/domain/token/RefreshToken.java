package com.rserver.miniblog.domain.token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue
    @Column(name = "refresh_token_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 512)
    private String refreshToken;

    @Column(nullable = false)
    private String deviceInfo;

    @Column(nullable = false, length = 45)
    private String ipAddress;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    private RefreshToken (Long memberId, String refreshToken, String deviceInfo, String ipAddress, LocalDateTime expireAt) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
        this.deviceInfo = deviceInfo;
        this.ipAddress = ipAddress;
        this.expireAt = expireAt;
    }

    public static RefreshToken create (Long memberId, String refreshToken, String deviceInfo, String ipAddress, long refreshTokenExpirationMillis) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireAt = now.plus(refreshTokenExpirationMillis, ChronoUnit.MILLIS);

        return new RefreshToken(memberId, refreshToken, deviceInfo, ipAddress, expireAt);
    }

}
