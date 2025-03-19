package com.rserver.miniblog.common;

import org.springframework.http.HttpStatus;

public interface BaseMessage {
    HttpStatus getStatus();
    String getMessage();
}
