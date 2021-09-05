package com.midorlo.k9.configuration.core;

import com.midorlo.k9.exception.K9UncheckedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdviceConfiguration {

    @ExceptionHandler(value = K9UncheckedException.class)
    public ResponseEntity<Object> exception(K9UncheckedException exception) {
        return new ResponseEntity<>("<img src=\"https://memegenerator" +
                ".net/img/instances/75486519/fickst-du-mit-ihr-dann-fickst-du-mit-mir-.jpg\"/>",
                HttpStatus.BAD_REQUEST);
    }
}
