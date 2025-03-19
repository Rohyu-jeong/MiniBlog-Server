package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;

public class UnauthorizedException extends BaseErrorException{
    public UnauthorizedException(BaseMessage errorMessage) {
        super(errorMessage);
    }
}
