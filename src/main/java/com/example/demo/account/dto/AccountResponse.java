package com.example.demo.account.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private BigDecimal plnAccount;
    private BigDecimal usdAccount;
}
