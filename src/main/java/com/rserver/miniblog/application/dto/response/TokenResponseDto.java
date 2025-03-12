package com.rserver.miniblog.application.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenResponseDto {
    private final String accessToken;
    private final String refreshToken;

    public static TokenResponseDto of (String accessToken, String refreshToken) {
        return new TokenResponseDto(accessToken, refreshToken);
    }

}
