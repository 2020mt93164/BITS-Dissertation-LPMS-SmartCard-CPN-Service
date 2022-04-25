package com.cpn.service;

import lombok.Data;

@Data
public class Card {
    private long id;
    private String cardNo;
    private String cardName;
    private long expMonth;
    private long expYear;
    private long cvv;
    private long availablePoints;

}

