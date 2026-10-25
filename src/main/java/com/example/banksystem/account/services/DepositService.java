package com.example.banksystem.account.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.DepositDTO;
import com.example.banksystem.account.transactions.AccountEntry;
import com.example.banksystem.account.transactions.Transaction;
import com.example.banksystem.account.transactions.TransactionType;
import com.example.banksystem.account.transactions.repositories.AccountEntryRepository;
import com.example.banksystem.account.transactions.repositories.TransactionRepository;
import com.example.banksystem.exception.AccountNotFoundException;
import com.example.banksystem.exception.CantBeZeroOrLessException;

import jakarta.transaction.Transactional;

@Service
@Transactional
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
	public String execute(Integer id, DepositDTO request) {
		
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			
			if (request.getAmount() == null
			        || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			    throw new CantBeZeroOrLessException();
			}
			account.get().setBalance(account.get().getBalance().add(request.getAmount()));
			accountRepository.save(account.get());
			
			Transaction transaction = new Transaction();
			transaction.setTransactionType(TransactionType.TRANSACTION_TYPE_DEPOSIT.getMessage());
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
