package com.example.demo.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    private String firstName;
    private String lastName;
    private BigDecimal plnAccount;

}
