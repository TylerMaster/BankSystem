package com.example.banksystem.account.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.RetrieveAccountEntryResponse;
import com.example.banksystem.account.transactions.AccountEntry;
import com.example.banksystem.account.transactions.repositories.AccountEntryRepository;
import com.example.banksystem.exception.AccountNotFoundException;

@Service
public class GetAccountEntriesService implements Query<Integer, List<RetrieveAccountEntryResponse>> {

	
	private final AccountEntryRepository accountEntryRepository;
	private final AccountRepository accountRepository;
	
	public GetAccountEntriesService(AccountEntryRepository accountEntryRepository, AccountRepository accountRepository){
		
		this.accountEntryRepository = accountEntryRepository;
		this.accountRepository = accountRepository;
		
	}
	
	@Override
	public List<RetrieveAccountEntryResponse> execute(Integer id) {
		
		Optional<Account> account = accountRepository.findById(id);
		
		if (account.isPresent()) {
			List<AccountEntry> entries = accountEntryRepository.findByAccountId(id);
			List<RetrieveAccountEntryResponse> response = new ArrayList<>();
			for(AccountEntry a : entries) {
				response.add(new RetrieveAccountEntryResponse(a.getTransaction().getId(), a.getTransaction().getTransactionType(),
						a.getTransaction().getCreatedAt(), a.getAmount()));
			}
			
			return response;
			
		}
		throw new AccountNotFoundException();
		
	}
	
}
