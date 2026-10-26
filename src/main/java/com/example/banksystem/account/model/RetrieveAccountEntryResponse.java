package com.example.banksystem.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.banksystem.account.transactions.Transaction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RetrieveAccountEntryResponse {
	
	 private Integer transactionId;
	 
	 private String transactionType;
	 
	 private LocalDateTime createdAt;
	 
	 private BigDecimal amount;
}
