package com.example.banksystem.account.transactions;

import java.math.BigDecimal;

import com.example.banksystem.account.model.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_entries")
@Getter
@Setter
public class AccountEntry {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "transaction_id", nullable = false)
	    private Transaction transaction;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 	@JoinColumn(name = "account_id", nullable = false)
	 	private Account account;
	 
	 @Column(nullable = false, precision = 19, scale = 2)
	 private BigDecimal amount;
}
