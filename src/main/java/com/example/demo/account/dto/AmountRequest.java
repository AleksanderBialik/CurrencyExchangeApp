package com.example.demo.account.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AmountRequest {

    @NotNull(message = "amount must be present")
    @Min(message = "amount value must be larger than 1", value = 1)
    private BigDecimal amount;
}
