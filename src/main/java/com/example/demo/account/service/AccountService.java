package com.example.demo.account.service;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.model.AccountEntity;
import com.example.demo.services.nbp.model.Rates;
import com.example.demo.services.nbp.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ExchangeRatesService exchangeRatesService;

    public void createAccount(AccountEntity account){
        accountRepository.save(account);
    }

    public AccountEntity getAccountById(UUID id){
        return accountRepository.getAccountById(id);
    }

    public ResponseEntity<Object> exchangeToPln(AccountEntity entity, BigDecimal amount){
        Rates rates = exchangeRatesService.getExchangeRates();
        MathContext m = new MathContext(4);
        entity.setUsdAccount(entity.getUsdAccount().subtract(amount));
        entity.setPlnAccount(entity.getPlnAccount().add(amount.multiply(rates.getBid(),m)));
        accountRepository.save(entity);
        return ResponseEntity.status(200).body("{\"message\":\"USD was successfully exchanged to PLN\"}");
    }

    public ResponseEntity<Object> exchangeToUsd(AccountEntity entity, BigDecimal amount){
        Rates rates = exchangeRatesService.getExchangeRates();
        MathContext m = new MathContext(4);
        entity.setPlnAccount(entity.getPlnAccount().subtract(amount));
        entity.setUsdAccount(entity.getUsdAccount().add(amount.divide(rates.getAsk(), m)));
        accountRepository.save(entity);

        return ResponseEntity.status(200).body("{\"message\":\"PLN was successfully exchanged to USD\"}");
    }
}
