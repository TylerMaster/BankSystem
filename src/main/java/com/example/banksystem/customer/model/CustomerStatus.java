package com.example.banksystem.customer.model;

public enum CustomerStatus {
	ACTIVE("ACTIVE"),
	DISABLED("DISABLED");

	private final String message;


	CustomerStatus(String message){
		this.message = message;
		
	}

	public String getMessage() {
		return message;
}
}
