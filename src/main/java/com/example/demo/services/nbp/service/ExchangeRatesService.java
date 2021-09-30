package com.example.demo.services.nbp.service;

import com.example.demo.services.nbp.model.Rates;
import com.example.demo.services.nbp.model.RatesList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExchangeRatesService {

    @Value("${nbpapi.url}")
    private String apiUrl;


    public Rates getExchangeRates(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RatesList> result = restTemplate.getForEntity(apiUrl, RatesList.class);
        return result.getBody().get(0);

    }
}
