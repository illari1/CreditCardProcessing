package com.publicis.microservices.creditcards.service;

import com.publicis.microservices.creditcards.CardUtils;
import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;
import com.publicis.microservices.creditcards.domain.entity.Card;
import com.publicis.microservices.creditcards.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.swing.text.Utilities;
import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardServiceTest {

    private static int CARD_ID = 1;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private CardService cardService;


    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldCreateNewCreditCard() {
        RegistrationCardRequest registrationCardRequest = CardUtils.buildCardRequest();
        when(cardRepository.save(any(Card.class))).thenReturn(Card.builder().id(CARD_ID)
                                                                            .balance(new BigDecimal(0))
                                                                            .build());
        Card newCard = cardService.registrationCard(registrationCardRequest);
        assertEquals(CARD_ID, newCard.getId());
        assertEquals(new BigDecimal(0), newCard.getBalance());
        verify(cardRepository).save(any(Card.class));
        verify(validationService).validateCardNumber(registrationCardRequest.getCardNumber());
    }

    @Test
    void getAllCards() {
        when(cardRepository.findAll()).thenReturn(List.of(CardUtils.buildCard()));
        List<Card> cards = cardService.getAllCards();
        assertEquals(1, cards.size());
        verify(cardRepository).findAll();
    }
}