package com.publicis.microservices.creditcards.api.controller;

import com.publicis.microservices.creditcards.api.controller.dto.RegistrationCardRequest;
import com.publicis.microservices.creditcards.api.controller.dto.RegistrationResponse;
import com.publicis.microservices.creditcards.entity.Card;
import com.publicis.microservices.creditcards.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1")
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Register a new credit card")
    @PostMapping(value = "/cards" , produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Returns the cardId")

    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<RegistrationResponse> RegisterCard(@RequestBody RegistrationCardRequest registrationCardRequest) {
        log.info("Registering a card, payload: {}", registrationCardRequest);
        Card newCard= cardService.registrationCard(registrationCardRequest);
        RegistrationResponse response = RegistrationResponse.builder().cardId(newCard.getId()).build();
        log.info("End registration, response: {} status: ", response, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

