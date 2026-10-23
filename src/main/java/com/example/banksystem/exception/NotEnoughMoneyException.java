package com.example.banksystem.exception;

public class NotEnoughMoneyException extends RuntimeException {

	
	public NotEnoughMoneyException(){
		super(ErrorMessages.NOT_ENOUGH_MONEY.getMessage());
	}
}
