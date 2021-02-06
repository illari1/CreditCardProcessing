package com.publicis.microservices.creditcards.api.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationResponse {
    private int cardId;
}
