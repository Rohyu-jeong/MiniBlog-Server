package com.rserver.miniblog.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum SuccessMessage implements BaseMessage{
    SUCCESS(200, "SUCCESS");

    private final int status;
    private final String message;
}
