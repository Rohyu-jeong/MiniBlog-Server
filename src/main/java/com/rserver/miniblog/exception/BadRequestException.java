package com.rserver.miniblog.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException (String message) {
        super(message);
    }
}
