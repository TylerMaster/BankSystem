package com.example.banksystem.account.transactions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banksystem.account.transactions.AccountEntry;

public interface AccountEntryRepository extends JpaRepository<AccountEntry, Integer> {

    List<AccountEntry> findByAccountId(Long accountId);
}