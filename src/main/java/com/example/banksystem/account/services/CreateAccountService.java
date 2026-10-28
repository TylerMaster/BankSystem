package com.example.banksystem.account.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.CreateAccountRequest;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.CustomerNotFoundException;
@Service
public class CreateAccountService implements TwoParamCommand<CreateAccountRequest, CreateAccountResponse>{

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	
	public CreateAccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		
	}
	
	@Override
	public CreateAccountResponse execute(Integer input,CreateAccountRequest request) {
		Optional<Customer> customerOptional = customerRepository.findById(input);
		if(customerOptional.isPresent()) {
			
			
			Customer customer = customerOptional.get();
			Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();
		  
		  String authenticatedEmail = authentication.getName();
		  
		  if(!customer.getEmail()
				  .equalsIgnoreCase(authenticatedEmail)) {
			  
			  throw new AccessDeniedException(
					  "You are not authorized to access this user's accounts");
		  }
			
			
			Account account = new Account();
			account.setAccountNumber(request.getAccountNumber());
			account.setBalance(BigDecimal.ZERO);
			account.setStatus("ACTIVE");
			account.setCustomer(customer);
			
			Account savedAccount = accountRepository.save(account);
			
			return new CreateAccountResponse(
					savedAccount.getId(),
					savedAccount.getAccountNumber(),
					savedAccount.getBalance(),
					savedAccount.getStatus(),
					savedAccount.getCustomer().getId());
		}
			throw new CustomerNotFoundException();
	}
}
