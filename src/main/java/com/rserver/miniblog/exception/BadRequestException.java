package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;

public class BadRequestException extends BaseErrorException{
    public BadRequestException(BaseMessage errorMessage) {
        super(errorMessage);
    }
}
