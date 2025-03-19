package com.rserver.miniblog.exception;

import com.rserver.miniblog.common.BaseMessage;

public class ImageUploadException extends BaseErrorException{
    public ImageUploadException(BaseMessage errorMessage) {
        super(errorMessage);
    }
}
