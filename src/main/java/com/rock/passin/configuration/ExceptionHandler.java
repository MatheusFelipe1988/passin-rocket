package com.rock.passin.configuration;

import com.rock.passin.domain.exception.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    public ResponseEntity handlerExceptionNotFound(EventNotFoundException exception){
        return ResponseEntity.ok().build();
    }
}
