package com.example.banksystem.account.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.account.model.UpdateAccountCommand;

@Service
public class UpdateAccountService implements TwoParamCommand<UpdateAccountCommand, CreateAccountResponse> {

	private final AccountRepository accountRepository;
	
	public UpdateAccountService(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}
	
	@Override
	public CreateAccountResponse execute(Integer id, UpdateAccountCommand input) {
		
		Optional<Account> account = accountRepository.findById(id);
		return null;
		
	}
}
