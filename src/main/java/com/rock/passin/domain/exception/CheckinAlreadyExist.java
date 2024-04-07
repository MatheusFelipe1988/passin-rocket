package com.rock.passin.domain.exception;

public class CheckinAlreadyExist extends RuntimeException{
    public CheckinAlreadyExist(String message){
        super(message);
    }
}
