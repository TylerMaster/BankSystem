package com.example.banksystem.account.services;

import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.account.model.UpdateAccountCommand;
import com.example.banksystem.exception.AccountNotFoundException;

@Service
public class UpdateAccountService implements TwoParamCommand<UpdateAccountCommand, CreateAccountResponse> {

	private final AccountRepository accountRepository;
	
	public UpdateAccountService(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}
	
	@Override
	public CreateAccountResponse execute(Integer id, UpdateAccountCommand input) {
		
		Optional<Account> optionalAccount = accountRepository.findById(id);
		
	
		if(optionalAccount.isPresent()) {
			Account account = new Account();
			account.setId(input.getId());
			account.setAccountNumber(input.getAccount().getAccountNumber());
			account.setBalance(optionalAccount.get().getBalance());
			account.setStatus(input.getAccount().getStatus());
			account.setCustomer(optionalAccount.get().getCustomer());
			accountRepository.save(account);
			
			CreateAccountResponse response = new CreateAccountResponse();
			response.setId(input.getId());
			response.setAccountNumber(input.getAccount().getAccountNumber());
			response.setBalance(optionalAccount.get().getBalance());
			response.setStatus(optionalAccount.get().getStatus());
			response.setCustomerId(optionalAccount.get().getCustomer().getId());
			return response;
		}
		throw new AccountNotFoundException();
		
	}
}
