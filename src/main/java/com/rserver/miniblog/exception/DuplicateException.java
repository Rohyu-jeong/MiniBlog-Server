package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;

public class DuplicateException extends BaseErrorException{
    public DuplicateException(BaseMessage errorMessage) {
        super(errorMessage);
    }
}
