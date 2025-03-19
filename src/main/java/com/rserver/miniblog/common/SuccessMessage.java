package com.rserver.miniblog.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum SuccessMessage implements BaseMessage{
    SUCCESS(HttpStatus.OK, "SUCCESS");

    private final HttpStatus status;
    private final String message;
}
