package com.example.banksystem.exception;


public enum ErrorMessages {
	DUPLICATE_CUSTOMER_EMAIL("Email already exists"),
	//FIRST_NAME_REQUIRED("First name is required"),
    //LAST_NAME_REQUIRED("Last name is required"),
    //EMAIL_REQUIRED("Email is required"),
    //EMAIL_INVALID("Email must be valid"),
    CUSTOMER_NOT_FOUND("Customer not found"),
    NOT_ENOUGH_MONEY("Insufficient funds"),
    CANT_BE_ZERO_OR_LESS("Value can't be zero or less"),
    CANNOT_TRANSFER_FUNDS("Cannot transfer funds"),
	ACCOUNT_NOT_FOUND("Account not found");
  
	
	private final String message;
	
	
	ErrorMessages(String message){
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}
}
