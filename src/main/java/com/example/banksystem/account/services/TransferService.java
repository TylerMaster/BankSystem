package com.example.banksystem.account.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.TransferDTO;
import com.example.banksystem.exception.AccountNotFoundException;
import com.example.banksystem.exception.CannotTransferException;
import com.example.banksystem.exception.NotEnoughMoneyException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferService implements TwoParamCommand<TransferDTO, String>{

	private final AccountRepository accountRepository;
	
	public TransferService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		
	}
	@Override
	public String execute(Integer fromAccountId, TransferDTO input){
		Optional<Account> fromAccount = accountRepository.findById(fromAccountId);
		Optional<Account> toAccount = accountRepository.findByAccountNumber(input.getToAccountNumber());
		if(fromAccount.isPresent() && toAccount.isPresent()) {
			if(fromAccount.get().getId() == toAccount.get().getId()) {
				throw new CannotTransferException();
				
			}
			Account from = fromAccount.get();
			Account to = toAccount.get();
			
			add(to, input.getAmount());
			deduct(from, input.getAmount());
			
			return "Success";
		}
		
		
		
		throw new AccountNotFoundException();
	}
	
	private void deduct(Account account, BigDecimal amount) {
		if(amount.compareTo(account.getBalance()) > 0) {
			throw new NotEnoughMoneyException();
		}
		account.setBalance(account.getBalance().subtract(amount));
		
	}
	private void add(Account account, BigDecimal amount) {
		account.setBalance(account.getBalance().add(amount));
		
	}
}
