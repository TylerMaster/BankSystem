package com.example.banksystem.exception;

public class CannotTransferException extends RuntimeException{
	
public CannotTransferException(){
	super(ErrorMessages.CANNOT_TRANSFER_FUNDS.getMessage());
}
}
