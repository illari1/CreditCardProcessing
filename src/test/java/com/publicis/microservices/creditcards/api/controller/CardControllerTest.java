package com.publicis.microservices.creditcards.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.microservices.creditcards.CardUtils;
import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;
import com.publicis.microservices.creditcards.domain.entity.Card;
import com.publicis.microservices.creditcards.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(CardController.class)
class CardControllerTest {

    private static int CARD_ID = 1;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CardService cardService;

    @BeforeEach
    void setup() {
    }

    @Test
    void shouldCreateNewCreditCard() throws Exception {

        RegistrationCardRequest registrationCardRequest = CardUtils.buildCardRequest();
        when(cardService.registrationCard(registrationCardRequest)).thenReturn(CardUtils.buildCard());

        mockMvc.perform(
                    post("/api/v1/cards")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(registrationCardRequest)))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.cardId").value(CARD_ID));

    }

    @Test
    void getAllCards() throws Exception {
        when(cardService.getAllCards()).thenReturn(List.of(CardUtils.buildCard()));
        mockMvc.perform(
                get("/api/v1/cards"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}

