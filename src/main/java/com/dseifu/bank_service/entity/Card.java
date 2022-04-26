package com.dseifu.bank_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardId;
    private String cardNumber;
    private String cardType;
    private Date expiryDate;
    private String ccv;
    private String preferredAuthentication;
    private boolean isBlocked;
}
