package com.publicis.microservices.creditcards.errorhandling;

import com.publicis.microservices.creditcards.domain.error.CardErrorResponse;
import com.publicis.microservices.creditcards.domain.exception.InvalidCardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerService {

    @ExceptionHandler(value = {InvalidCardException.class})
    ResponseEntity<CardErrorResponse> invalidCardException (InvalidCardException e) {
        HttpStatus status = HttpStatus.resolve(Integer.valueOf(e.getCardErrorResponse().getStatus()));
        return new ResponseEntity<>(e.getCardErrorResponse(), status);
    }

}
