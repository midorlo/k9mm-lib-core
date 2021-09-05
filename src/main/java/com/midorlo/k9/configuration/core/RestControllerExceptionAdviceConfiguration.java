package com.midorlo.k9.configuration.core;

import com.midorlo.k9.exception.K9UncheckedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionAdviceConfiguration {

    static String DEFAULT_MSG =
            "In the words of lyrical coryphaeus Farid Hamed El Abdellaoui: Fickst du mit mir?  Dann fickst du mit mir!";

    @ExceptionHandler(value = K9UncheckedException.class)
    public ResponseEntity<Object> exception(K9UncheckedException exception) {
        return new ResponseEntity<>(DEFAULT_MSG, HttpStatus.BAD_REQUEST);
    }
}
