package com.example.demo;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.model.AccountEntity;
import com.example.demo.account.service.AccountService;
import com.example.demo.services.nbp.model.Rates;
import com.example.demo.services.nbp.service.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyExchangeApplicationTests {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ExchangeRatesService exchangeRatesService;


    @Test
    void testGettingAccountById() {
        UUID uuid = UUID.randomUUID();
        AccountEntity acc = new AccountEntity(uuid,"Aleksander","Bialik", new BigDecimal("100"),new BigDecimal("100"));
        when(accountRepository.getAccountById(uuid)).thenReturn(acc);
        AccountEntity accountEntity = accountService.getAccountById(uuid);
        assertEquals(uuid, accountEntity.getId());
    }

    @Test
    void testRetrievingRates(){
        Rates r = new Rates(new BigDecimal("3.9999"), new BigDecimal("4.0123"));
        when(exchangeRatesService.getExchangeRates()).thenReturn(r);
        Rates rates = accountService.getExchangeRates();
        assertEquals(r.getAsk(),rates.getAsk());
    }



}
