package com.example.demo.account.controller;

import com.example.demo.account.dto.AccountRequest;
import com.example.demo.account.model.AccountEntity;
import com.example.demo.account.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AccountService accountService;

    @PostMapping()
    public ResponseEntity<Object> createAccount(@RequestBody AccountRequest request){

        AccountEntity entity = modelMapper.map(request,AccountEntity.class);
        entity.setUsdAccount(new BigDecimal(0));
        accountService.createAccount(entity);

        return ResponseEntity.status(200).body("{\"message\":\"account was created\"}");
    }

}
