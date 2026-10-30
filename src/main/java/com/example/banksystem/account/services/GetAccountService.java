package com.example.banksystem.account.services;

import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.exception.AccountNotFoundException;

@Service
public class GetAccountService implements Query<Integer, CreateAccountResponse> {

	
	private final AccountRepository accountRepository;
	
	public GetAccountService(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}
	
	@Override
	public CreateAccountResponse execute(Integer id) {
		
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			  Authentication authentication =
		                SecurityContextHolder.getContext().getAuthentication();
			  
			  String authenticatedEmail = authentication.getName();
			  
			  boolean isAdmin = authentication.getAuthorities().stream()
				        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
			 
		if(!isAdmin) {
			  if(!account.get().getCustomer().getEmail()
					  .equalsIgnoreCase(authenticatedEmail)) {
				  
				  throw new AccessDeniedException(
						  "You are not authorized to access this account");
			  }
		}
			return new CreateAccountResponse(account.get().getId(),
											 account.get().getAccountNumber(), 
											 account.get().getBalance(), 
											 account.get().getStatus(), 
											 account.get().getCustomer().getId());
			
		}
		throw new AccountNotFoundException();
	}
	
	
}
