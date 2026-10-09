package com.example.banksystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateCustomerEmailException extends RuntimeException {

	private static final Logger logger = LoggerFactory.getLogger(DuplicateCustomerEmailException.class);
	public DuplicateCustomerEmailException(){
		
		super(ErrorMessages.DUPLICATE_CUSTOMER_EMAIL.getMessage());
		logger.error("Exception " + getClass() + " thrown");
	}
}
