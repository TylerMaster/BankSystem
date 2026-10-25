package com.example.banksystem.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CannotTransferException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	
	public ErrorResponse handleCannotTransferException(CannotTransferException exception) {
		
		return new ErrorResponse(exception.getMessage());
	}
	
	@ExceptionHandler(CantBeZeroOrLessException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	
	public ErrorResponse handleCantBeZeroOrLessException(CantBeZeroOrLessException exception) {
		
		return new ErrorResponse(exception.getMessage());
	}
	
	@ExceptionHandler(NotEnoughMoneyException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	
	public ErrorResponse handleNotEnoughMoneyException(NotEnoughMoneyException exception) {
		
		return new ErrorResponse(exception.getMessage());
	}
	
	
	@ExceptionHandler(DuplicateCustomerEmailException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	
	public ErrorResponse handleDuplicateCustomerEmailException(DuplicateCustomerEmailException exception){
		return new ErrorResponse(exception.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleValidationException(
	        MethodArgumentNotValidException exception) {

	    String message = exception.getBindingResult()
	            .getFieldErrors()
	            .get(0)
	            .getDefaultMessage();

	    return new ErrorResponse(message);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleCustomerNotFoundException(
	        CustomerNotFoundException exception) {

	    return new ErrorResponse(exception.getMessage());
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleAccountNotFoundException(AccountNotFoundException exception) {
		return new ErrorResponse(exception.getMessage());
		
	}
}
