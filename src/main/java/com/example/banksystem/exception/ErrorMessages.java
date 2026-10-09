package com.example.banksystem.exception;


public enum ErrorMessages {
	DUPLICATE_CUSTOMER_EMAIL("Email already exists");
	
	
	private final String message;
	
	
	ErrorMessages(String message){
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}
}
