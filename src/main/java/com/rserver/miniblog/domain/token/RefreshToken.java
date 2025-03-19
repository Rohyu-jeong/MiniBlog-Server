package com.rserver.miniblog.domain.token;

import com.rserver.miniblog.exception.InvalidTokenException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.rserver.miniblog.domain.token.TokenErrorMessage.*;

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

    private RefreshToken(Long memberId, String refreshToken, String deviceInfo, String ipAddress, LocalDateTime expireAt) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
        this.deviceInfo = deviceInfo;
        this.ipAddress = ipAddress;
        this.expireAt = expireAt;
    }

    public static RefreshToken create(Long memberId, String refreshToken, String deviceInfo, String ipAddress, long refreshTokenExpirationMillis) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireAt = now.plus(refreshTokenExpirationMillis, ChronoUnit.MILLIS);

        return new RefreshToken(memberId, refreshToken, deviceInfo, ipAddress, expireAt);
    }

    public void validate(String token, String deviceInfo, String ipAddress) {
        validateToken(token);
        validateDeviceInfo(deviceInfo);
        validateIpAddress(ipAddress);
        checkExpiration();
    }

    public void validateExpiration() {
        checkExpiration();
    }

    private void validateToken(String token) {
        if (!this.refreshToken.equals(token)) {
            throw new InvalidTokenException(TOKEN_MISMATCH.getMessage());
        }
    }

    private void validateDeviceInfo(String deviceInfo) {
        if (!this.deviceInfo.equals(deviceInfo)) {
            throw new InvalidTokenException(DEVICE_INFO_MISMATCH.getMessage());
        }
    }

    private void validateIpAddress(String ipAddress) {
        if (!this.ipAddress.equals(ipAddress)) {
            throw new InvalidTokenException(IP_MISMATCH.getMessage());
        }
    }

    private void checkExpiration() {
        if (LocalDateTime.now().isAfter(expireAt)) {
            throw new InvalidTokenException(TOKEN_EXPIRED.getMessage());
        }
    }

}
