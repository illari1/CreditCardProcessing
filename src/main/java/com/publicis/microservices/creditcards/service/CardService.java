package com.publicis.microservices.creditcards.service;

import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;
import com.publicis.microservices.creditcards.entity.Card;
import com.publicis.microservices.creditcards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card registrationCard(RegistrationCardRequest registrationCardRequest) {
        Card creditCard = Card.builder()
                .cardNumber(registrationCardRequest.getCardNumber())
                .limitCard(registrationCardRequest.getLimit())
                .balance(new BigDecimal(0))
                .name(registrationCardRequest.getName())
                .build();

        return  cardRepository.save(creditCard);
    }
}
