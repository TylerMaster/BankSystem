package com.example.banksystem.account.transactions;

public enum TransactionType {
TRANSACTION_TYPE_DEPOSIT("Deposit"),
TRANSACTION_TYPE_WITHDRAW("Withdraw"),
TRANSACTION_TYPE_TRANSFER("Transfer");


private final String message;


TransactionType(String message){
	this.message = message;
	
}

public String getMessage() {
	return message;
}
}
