package com.example.banksystem.account.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountResponse {
	private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private String status;
    private Integer customerId;
}
