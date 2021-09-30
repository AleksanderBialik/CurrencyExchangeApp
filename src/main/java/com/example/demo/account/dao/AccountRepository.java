package com.example.demo.account.dao;

import com.example.demo.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity getAccountById(UUID id);
}
