package com.example.demo.account.service;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.model.AccountEntity;
import com.example.demo.services.nbp.model.Rates;
import com.example.demo.services.nbp.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void exchangeToPln(AccountEntity entity, BigDecimal amount){
        Rates rates = exchangeRatesService.getExchangeRates();
        entity.setUsdAccount(entity.getUsdAccount().subtract(amount));
        entity.setPlnAccount(entity.getPlnAccount().add(amount.multiply(rates.getBid())));
        accountRepository.save(entity);
    }

    public void exchangeToUsd(AccountEntity entity, BigDecimal amount){
        Rates rates = exchangeRatesService.getExchangeRates();
        entity.setPlnAccount(entity.getPlnAccount().subtract(amount));
        entity.setUsdAccount(entity.getUsdAccount().add(amount.divide(rates.getAsk())));
        accountRepository.save(entity);
    }
}
