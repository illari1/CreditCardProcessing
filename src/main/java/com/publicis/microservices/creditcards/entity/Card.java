package com.publicis.microservices.creditcards.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Card")
@Value
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cardNumber" )
    private String cardNumber;

    @Column(name = "limitCard")
    private int limitCard;

    @Column(name = "balance")
    private BigDecimal balance;

}
