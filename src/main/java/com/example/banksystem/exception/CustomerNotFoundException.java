package com.example.banksystem.exception;

public class CustomerNotFoundException extends RuntimeException{

	
	public CustomerNotFoundException() {
		
		super(ErrorMessages.CUSTOMER_NOT_FOUND.getMessage());
	}
}
