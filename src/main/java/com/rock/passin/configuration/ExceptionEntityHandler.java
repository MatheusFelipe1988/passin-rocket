package com.rock.passin.configuration;

import com.rock.passin.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handlerExceptionNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity handlerExceptionFull(EventFullException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AttendeNotFoundException.class)
    public ResponseEntity handlerAttendeeNotFound(AttendeNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyException.class)
    public ResponseEntity handlerAttendeeAlreay(AttendeeAlreadyException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckinAlreadyExist.class)
    public ResponseEntity handlerCheckinAlreay(CheckinAlreadyExist exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
