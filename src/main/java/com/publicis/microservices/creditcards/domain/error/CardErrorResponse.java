package com.publicis.microservices.creditcards.domain.error;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@Builder
public class CardErrorResponse {
    private String status;
    private String code;
    private String message;
}
