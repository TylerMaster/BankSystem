package com.example.banksystem.account.transactions.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banksystem.account.transactions.AccountEntry;

public interface AccountEntryRepository extends JpaRepository<AccountEntry, Integer> {

	
	//@Query("SELECT a FROM AccountEntry a WHERE a.account.id = :accountId") <-- needs @Param in function parameter
	List<AccountEntry> findByAccountId(Integer accountId);
}