package com.example.banksystem.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banksystem.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}