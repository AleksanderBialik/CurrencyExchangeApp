package com.example.demo.account.service;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.model.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void createAccount(AccountEntity account){
        accountRepository.save(account);
    }

    public AccountEntity getAccountById(UUID id){
        return accountRepository.getAccountById(id);
    }
}
