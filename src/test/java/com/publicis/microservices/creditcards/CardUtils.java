package com.publicis.microservices.creditcards;

import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;
import com.publicis.microservices.creditcards.domain.entity.Card;

import java.math.BigDecimal;

public class CardUtils {

    private static String NAME = "Rosa Lopez";
    private static String CARD_NUMBER = "0123456789123456782";
    private static int LIMIT = 10000;
    private static BigDecimal BALANCE = new BigDecimal(0);

    public static RegistrationCardRequest buildCardRequest() {

        return RegistrationCardRequest.builder()
            .name(NAME)
            .cardNumber(CARD_NUMBER)
            .limit(LIMIT)
            .build();
    }

    public static Card buildCard() {

        return Card.builder()
                .name(NAME)
                .cardNumber(CARD_NUMBER)
                .limitCard(LIMIT)
                .balance(BALANCE)
                .build();
    }
}
