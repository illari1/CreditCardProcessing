package com.publicis.microservices.creditcards;

import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;

public class CardUtils {

    private static String NAME = "Rosa Lopez";
    private static String CARD_NUMBER = "0123456789123456782";
    private static int LIMIT = 10000;

    public static RegistrationCardRequest buildCardRequest() {

        return RegistrationCardRequest.builder()
            .name(NAME)
            .cardNumber(CARD_NUMBER)
            .limit(LIMIT)
            .build();
    }
}
