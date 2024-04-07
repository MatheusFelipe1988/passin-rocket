package com.rock.passin.domain.exception;

public class AttendeeAlreadyException extends RuntimeException{
    public AttendeeAlreadyException(String message){
        super(message);
    }
}
