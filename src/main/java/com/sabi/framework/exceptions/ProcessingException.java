package com.sabi.framework.exceptions;


/**
 *
 * This is an exception class
 */
public class ProcessingException extends FrameWorkApiException{
    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
