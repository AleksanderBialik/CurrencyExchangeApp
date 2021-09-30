package com.example.demo.services.nbp.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Rates {
    private BigDecimal bid;
    private BigDecimal ask;
}
