package com.publicis.microservices.creditcards.service;

import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;
import com.publicis.microservices.creditcards.domain.entity.Card;
import com.publicis.microservices.creditcards.domain.error.CardErrorResponse;
import com.publicis.microservices.creditcards.domain.exception.InvalidCardException;
import com.publicis.microservices.creditcards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ValidationService validationService;

    public Card registrationCard(RegistrationCardRequest registrationCardRequest) {
        validationService.validateCardNumber(registrationCardRequest.getCardNumber());
        Optional<Card> creditCardJpa = cardRepository.findByCardNumber(registrationCardRequest.getCardNumber());
        if (creditCardJpa.isPresent()) {
            CardErrorResponse cardErrorResponse = CardErrorResponse.builder()
                    .message("The supplied credit card number is already associated")
                    .code("credit-card-number-exists")
                    .status(String.valueOf(HttpStatus.CONFLICT.value()))
                    .build();

            throw new InvalidCardException(cardErrorResponse);
        }
        Card creditCard = Card.builder()
                .cardNumber(registrationCardRequest.getCardNumber())
                .limitCard(registrationCardRequest.getLimit())
                .balance(new BigDecimal(0))
                .name(registrationCardRequest.getName())
                .build();

        return cardRepository.save(creditCard);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}



