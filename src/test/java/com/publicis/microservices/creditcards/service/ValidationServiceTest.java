package com.publicis.microservices.creditcards.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    @Test
    void shouldCardValidateLuhn() {
        String cardnumber = "0985963357785083842";
        boolean isValidCard = ValidationService.validateLuhn(cardnumber);
        assertEquals(true, isValidCard);
    }

    @Test
    void shouldCardNoValidateLuhn() {
        String cardnumber = "3259518590658283455";
        boolean isValidCard = ValidationService.validateLuhn(cardnumber);
        assertEquals(false, isValidCard);
    }
}