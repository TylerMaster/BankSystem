package com.example.banksystem.account.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.DepositDTO;
import com.example.banksystem.exception.AccountNotFoundException;

@Service
public class DepositService implements TwoParamCommand<DepositDTO, String> {

	private final AccountRepository accountRepository;
	
	public DepositService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	@Override
	public String execute(Integer Id, DepositDTO request) {
		
		Optional<Account> account = accountRepository.findById(Id);
		if (account.isPresent()) {
			account.get().setBalance(account.get().getBalance().add(request.getAmount()));
			accountRepository.save(account.get());
			return "Success";
		}
		throw new AccountNotFoundException();
	}
}
