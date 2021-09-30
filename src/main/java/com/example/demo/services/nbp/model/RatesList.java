package com.example.demo.services.nbp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RatesList {
    private List<Rates> rates;

    public RatesList() {
        rates = new ArrayList<>();
    }

    public Rates get(int index){
        return rates.get(index);
    }
}
