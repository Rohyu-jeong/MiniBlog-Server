package com.rserver.miniblog.domain.token;

import com.rserver.miniblog.common.BaseMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorMessage implements BaseMessage {
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh Token 정보를 찾을 수가 없습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 일치하지 않습니다."),
    DEVICE_INFO_MISMATCH(HttpStatus.UNAUTHORIZED, "디바이스 정보가 일치하지 않습니다."),
    IP_MISMATCH(HttpStatus.UNAUTHORIZED, "IP 주소가 일치하지 않습니다."),
    BLACKLISTED_TOKEN(HttpStatus.FORBIDDEN, "블랙리스트에 등록된 토큰입니다.");

    private final HttpStatus status;
    private final String message;
}
