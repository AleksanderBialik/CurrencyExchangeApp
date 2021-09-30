package com.example.demo.account.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    @NotBlank (message = "firstName can't be empty ")
    @NotNull(message = "firstName must be present")
    private String firstName;

    @NotBlank (message = "lastName can't be empty ")
    @NotNull(message = "lastName must be present")
    private String lastName;

    @NotNull(message = "plnAccount must be present")
    @Min(message = "plnAccount's value must be larger than 1", value = 1)
    private BigDecimal plnAccount;

}
