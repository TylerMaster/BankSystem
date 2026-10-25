package com.example.banksystem.account.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.DepositDTO;
import com.example.banksystem.exception.AccountNotFoundException;
import com.example.banksystem.exception.NotEnoughMoneyException;

@Service
public class WithdrawalService implements TwoParamCommand<DepositDTO, String> {

	
	private final AccountRepository accountRepository;
	
	public WithdrawalService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		
	}
	
	@Override
	public String execute(Integer accountId, DepositDTO request) {
		Optional<Account> account = accountRepository.findById(accountId);
		if(account.isPresent()) {
			if(request.getAmount().compareTo(account.get().getBalance()) > 0) {
				throw new NotEnoughMoneyException();
			}
			
			account.get().setBalance(account.get().getBalance().subtract(request.getAmount()));
			accountRepository.save(account.get());
		return "success";
		}
		throw new AccountNotFoundException();
	}
		
}
