package com.example.banksystem.account.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.DepositDTO;
import com.example.banksystem.account.transactions.AccountEntry;
import com.example.banksystem.account.transactions.Transaction;
import com.example.banksystem.account.transactions.repositories.AccountEntryRepository;
import com.example.banksystem.account.transactions.repositories.TransactionRepository;
import com.example.banksystem.exception.AccountNotFoundException;

@Service
public class DepositService implements TwoParamCommand<DepositDTO, String> {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final AccountEntryRepository accountEntryRepository;
	
	public DepositService(AccountRepository accountRepository, TransactionRepository transactionRepository, AccountEntryRepository accountEntryRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.accountEntryRepository = accountEntryRepository;
	}
	@Override
	public String execute(Integer Id, DepositDTO request) {
		
		Optional<Account> account = accountRepository.findById(Id);
		if (account.isPresent()) {
			account.get().setBalance(account.get().getBalance().add(request.getAmount()));
			accountRepository.save(account.get());
			
			Transaction transaction = new Transaction();
			transaction.setTransactionType("Deposit");
			transaction.setCreatedAt(LocalDateTime.now());
			transactionRepository.save(transaction);
			
			
			AccountEntry accountEntry = new AccountEntry();
			accountEntry.setTransaction(transaction);
			accountEntry.setAccount(account.get());
			accountEntry.setAmount(request.getAmount());
			accountEntryRepository.save(accountEntry);
			
			return "Success";
		}
		throw new AccountNotFoundException();
	}
}
