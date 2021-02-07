package com.publicis.microservices.creditcards.domain.exception;
import com.publicis.microservices.creditcards.domain.error.CardErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidCardException extends RuntimeException {
    CardErrorResponse cardErrorResponse;
}
