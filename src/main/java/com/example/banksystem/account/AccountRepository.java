package com.example.banksystem.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banksystem.account.model.Account;

import jakarta.persistence.LockModeType;


public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	//@Query("SELECT a FROM Account a WHERE a.accountNumber LIKE %:keyword%")
	Optional<Account> findByAccountNumber(String accountNumber);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT a FROM Account a WHERE a.id = :id")
	Optional<Account> findByIdWithLock(@Param("id") Integer id);
	
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
	Optional<Account> findByAccountNumberWithLock(
	    @Param("accountNumber") String accountNumber
	);
	
}