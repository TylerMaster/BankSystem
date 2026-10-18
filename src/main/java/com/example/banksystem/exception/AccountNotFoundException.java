package com.example.banksystem.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException() {
		
		super(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage());
	}
}
