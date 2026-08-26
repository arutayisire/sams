package com.ulk.sams.exception;

public class SAMSException extends Exception {
    public SAMSException(String message) {
        super(message);
    }
    
    public SAMSException(String message, Throwable cause) {
        super(message, cause);
    }
}