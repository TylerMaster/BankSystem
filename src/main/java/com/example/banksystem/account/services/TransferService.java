package com.example.banksystem.account.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.TransferDTO;
import com.example.banksystem.account.transactions.AccountEntry;
import com.example.banksystem.account.transactions.Transaction;
import com.example.banksystem.account.transactions.TransactionType;
import com.example.banksystem.account.transactions.repositories.AccountEntryRepository;
import com.example.banksystem.account.transactions.repositories.TransactionRepository;
import com.example.banksystem.customer.model.CustomerStatus;
import com.example.banksystem.exception.AccountNotFoundException;
import com.example.banksystem.exception.CannotTransferException;
import com.example.banksystem.exception.CantBeZeroOrLessException;
import com.example.banksystem.exception.NotEnoughMoneyException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferService implements TwoParamCommand<TransferDTO, String>{

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final AccountEntryRepository accountEntryRepository;
	
	public TransferService(AccountRepository accountRepository, TransactionRepository transactionRepository, AccountEntryRepository accountEntryRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.accountEntryRepository = accountEntryRepository;
		
	}
	@Override
	public String execute(Integer fromAccountId, TransferDTO input){
		Optional<Account> fromAccount = accountRepository.findById(fromAccountId);
		Optional<Account> toAccount = accountRepository.findByAccountNumber(input.getToAccountNumber());
		
		Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
	  
	  String authenticatedEmail = authentication.getName();
	  boolean isAdmin = authentication.getAuthorities().stream()
		        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	 
if(!isAdmin) {
	  if(!fromAccount.get().getCustomer().getEmail()
			  .equalsIgnoreCase(authenticatedEmail)) {
		  
		  throw new AccessDeniedException(
				  "You are not authorized to access this user's accounts");
	  }
}

		if (!fromAccount.get().getCustomer().getStatus().equalsIgnoreCase(CustomerStatus.ACTIVE.getMessage())
					|| !toAccount.get().getCustomer().getStatus().equalsIgnoreCase(CustomerStatus.ACTIVE.getMessage())) {
				throw new CannotTransferException();
			}
		if (!fromAccount.get().getStatus().equalsIgnoreCase(CustomerStatus.ACTIVE.getMessage())
					|| !toAccount.get().getStatus().equalsIgnoreCase(CustomerStatus.ACTIVE.getMessage())) {
				throw new CannotTransferException();
			}
		if(fromAccount.isPresent() && toAccount.isPresent()) {
			if (fromAccount.get().getId().equals(toAccount.get().getId())) {
				throw new CannotTransferException();
				
			}
			
			 if (input.getAmount() == null
		                || input.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
		            throw new CantBeZeroOrLessException();
		    }
			 
			Account from = fromAccount.get();
			Account to = toAccount.get();
			
			add(to, input.getAmount());
			deduct(from, input.getAmount());
			
			Transaction transaction = new Transaction();
			transaction.setTransactionType(TransactionType.TRANSACTION_TYPE_TRANSFER.getMessage());
			transaction.setCreatedAt(LocalDateTime.now());
			transactionRepository.save(transaction);
			
			
			AccountEntry accountEntryFrom = new AccountEntry();
			accountEntryFrom.setTransaction(transaction);
			accountEntryFrom.setAccount(from);
			accountEntryFrom.setAmount(input.getAmount().negate());
			accountEntryRepository.save(accountEntryFrom);
			
			AccountEntry accountEntryTo = new AccountEntry();
			accountEntryTo.setTransaction(transaction);
			accountEntryTo.setAccount(to);
			accountEntryTo.setAmount(input.getAmount());
			accountEntryRepository.save(accountEntryTo);
			
			
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
