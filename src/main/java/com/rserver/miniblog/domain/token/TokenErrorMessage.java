package com.rserver.miniblog.domain.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenErrorMessage {

    TOKEN_NOT_FOUND("Refresh Token 정보를 찾을 수가 없습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    TOKEN_MISMATCH("리프레시 토큰이 일치하지 않습니다."),
    DEVICE_INFO_MISMATCH("디바이스 정보가 일치하지 않습니다."),
    IP_MISMATCH("IP 주소가 일치하지 않습니다."),
    BLACKLISTED_TOKEN("블랙리스트에 등록된 토큰입니다.");

    private final String message;
}
