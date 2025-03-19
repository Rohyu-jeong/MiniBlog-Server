package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;

public class NotFoundException extends BaseErrorException{
    public NotFoundException(BaseMessage errorMessage) {
        super(errorMessage);
    }
}
