package com.example.banksystem.account.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.CustomerNotFoundException;

@Service
public class GetAllCustomerAccountsService implements Query<Integer, List<CreateAccountResponse>> {

	
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	
	public GetAllCustomerAccountsService(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		
	}
	
	@Override
	public List<CreateAccountResponse> execute(Integer input){
		
		Optional<Customer> customer = customerRepository.findById(input);
		if(customer.isPresent()) {
			  Authentication authentication =
		                SecurityContextHolder.getContext().getAuthentication();
			  
			  String authenticatedEmail = authentication.getName();
			  
			  if(!customer.get().getEmail()
					  .equalsIgnoreCase(authenticatedEmail)) {
				  
				  throw new AccessDeniedException(
						  "You are not authorized to access this user's accounts");
			  }
			
			List<Account> accounts = customer.get().getAccounts();
			List<CreateAccountResponse> responseList = new ArrayList<>();
			for(Account a : accounts) {
				
				CreateAccountResponse response = new CreateAccountResponse(a.getId(), a.getAccountNumber(), a.getBalance(), a.getStatus(), a.getCustomer().getId());
				responseList.add(response);
			}
			return responseList;
			
		}
		throw new CustomerNotFoundException();
		
	}
}
