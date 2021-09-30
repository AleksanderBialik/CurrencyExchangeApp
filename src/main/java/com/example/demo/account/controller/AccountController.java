package com.example.demo.account.controller;

import com.example.demo.account.dto.AccountRequest;
import com.example.demo.account.dto.AccountResponse;
import com.example.demo.account.dto.AmountRequest;
import com.example.demo.account.model.AccountEntity;
import com.example.demo.account.service.AccountService;
import com.example.demo.services.nbp.service.ExchangeRatesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AccountService accountService;

    @PostMapping()
    public ResponseEntity<Object> createAccount(@RequestBody @Valid AccountRequest request){

        AccountEntity entity = modelMapper.map(request,AccountEntity.class);
        entity.setUsdAccount(new BigDecimal(0));
        accountService.createAccount(entity);

        return ResponseEntity.status(200).body("{\"message\":\"account was created\"}");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccount(@PathVariable("id") UUID id ){
        AccountEntity entity = accountService.getAccountById(id);
        if(entity == null){
            return ResponseEntity.status(400).body("{\"message\":\"account does not exist\"}");
        }
        AccountResponse response = modelMapper.map(entity,AccountResponse.class);

        return ResponseEntity.status(200).body(response);
    }
    @PutMapping("/{id}/pln-to-usd")
    public ResponseEntity<Object> exchangePlnToUsd(@PathVariable("id") UUID id, @RequestBody @Valid AmountRequest amountRequest){
        AccountEntity entity = accountService.getAccountById(id);
        if(entity == null){
            return ResponseEntity.status(400).body("{\"message\":\"account does not exist\"}");
        }
        if(amountRequest.getAmount().compareTo(entity.getPlnAccount()) > 0){
            return ResponseEntity.status(400).body("{\"message\":\"not enough money on PLN account\"}");
        }

        return accountService.exchangeToUsd(entity, amountRequest.getAmount());
    }
    @PutMapping("/{id}/usd-to-pln")
    public ResponseEntity<Object> exchangeUsdToPln(@PathVariable("id") UUID id, @RequestBody @Valid AmountRequest amountRequest){
        AccountEntity entity = accountService.getAccountById(id);
        if(entity == null){
            return ResponseEntity.status(400).body("{\"message\":\"account does not exist\"}");
        }
        if(amountRequest.getAmount().compareTo(entity.getUsdAccount()) > 0){
            return ResponseEntity.status(400).body("{\"message\":\"not enough money on USD account\"}");
        }

        return accountService.exchangeToPln(entity, amountRequest.getAmount());
    }
}
