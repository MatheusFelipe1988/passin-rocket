package com.rock.passin.domain.exception;

public class EventFullException extends RuntimeException{
    public EventFullException(String message){
        super(message);
    }
}
