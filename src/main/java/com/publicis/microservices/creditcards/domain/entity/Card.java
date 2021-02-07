package com.publicis.microservices.creditcards.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Card")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length=26)
    private String name;

    @Column(name = "cardNumber", nullable = false, unique = true, length=32)
    private String cardNumber;


    @Column(name = "limitCard", nullable = false)
    private int limitCard;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

}
