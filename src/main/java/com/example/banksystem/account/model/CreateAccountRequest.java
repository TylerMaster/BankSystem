package com.example.banksystem.account.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAccountRequest {

	
	@NotBlank(message = "Account number is required")
	@Size(max = 20, message = "Account number cannot exceed 20 characters")
	private String accountNumber;
	
	public String getAccountNumber() {
		
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
