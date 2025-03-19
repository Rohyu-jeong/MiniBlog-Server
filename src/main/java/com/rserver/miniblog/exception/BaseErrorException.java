package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;
import lombok.Getter;

@Getter
public class BaseErrorException extends RuntimeException{
    private final BaseMessage errorMessage;

    public BaseErrorException(BaseMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
