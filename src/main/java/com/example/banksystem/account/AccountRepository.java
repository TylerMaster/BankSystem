package com.example.banksystem.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banksystem.account.model.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	//@Query("SELECT a FROM Account a WHERE a.accountNumber LIKE %:keyword%")
	Optional<Account> findByAccountNumber(String accountNumber);
}