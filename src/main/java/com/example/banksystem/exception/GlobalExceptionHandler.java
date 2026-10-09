package com.example.banksystem.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateCustomerEmailException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	
	public ErrorResponse handleDuplicateCustomerEmailException(DuplicateCustomerEmailException exception){
		return new ErrorResponse(exception.getMessage());
	}
}
