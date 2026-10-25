package com.example.banksystem.exception;

public class CantBeZeroOrLessException extends RuntimeException{
	
	public CantBeZeroOrLessException() {
		
		super(ErrorMessages.CANT_BE_ZERO_OR_LESS.getMessage());
		
	}

}
