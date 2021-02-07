package com.publicis.microservices.creditcards.service;

import com.publicis.microservices.creditcards.domain.exception.InvalidCardException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ValidationServiceTest {
    private static String CARD_NUMBER_VALID = "0985963357785083842";
    private static String CARD_NUMBER_FORMAT_INCORRECT = "098596335778S08R384";
    private static String CARD_NUMBER_INVALID  = "3259518590658283455";

    @InjectMocks
    ValidationService validationService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }
    @Test
    void shouldCardValidateLuhn() {
        String cardnumber = CARD_NUMBER_VALID;
        boolean isValidCard = ValidationService.validateLuhn(cardnumber);
        assertEquals(true, isValidCard);
    }

    @Test
    void shouldCardNoValidateLuhn() {
        String cardnumber = CARD_NUMBER_INVALID;
        boolean isValidCard = ValidationService.validateLuhn(cardnumber);
        assertEquals(false, isValidCard);
    }

    @Test
    void shouldCardNumberOnlyDigits() {
        String cardnumber = CARD_NUMBER_VALID;
        boolean isOnlyNumberDigits = ValidationService.onlyDigits(cardnumber);
        assertEquals(true, isOnlyNumberDigits);
    }

    @Test
    void cardNumberIsNotOnlyDigits() {
        String cardnumber = CARD_NUMBER_FORMAT_INCORRECT;
        boolean isOnlyNumberDigits = ValidationService.onlyDigits(cardnumber);
        assertEquals(false, isOnlyNumberDigits);
    }

    @Test
    void shouldFailWhenCardNumberIsInvalid() {
        Assertions.assertThrows(InvalidCardException.class, () -> validationService.validateCardNumber(CARD_NUMBER_INVALID));

    }
}