package com.publicis.microservices.creditcards.api.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationCardRequest {
    private String name;
    private String cardNumber;
    private int limit;
}
