package com.rserver.miniblog.common;

import org.springframework.http.HttpStatus;

public interface BaseMessage {
    int getStatus();
    String getMessage();
}
