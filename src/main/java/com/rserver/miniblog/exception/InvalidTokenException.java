package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;

public class InvalidTokenException extends BaseErrorException {
    public InvalidTokenException(BaseMessage errorMessage) {
        super(errorMessage);
    }
}
