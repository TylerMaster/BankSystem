package com.example.banksystem.account.transactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banksystem.account.transactions.Transaction;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
}