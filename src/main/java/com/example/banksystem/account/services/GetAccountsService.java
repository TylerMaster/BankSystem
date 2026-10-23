package com.example.banksystem.account.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.account.AccountRepository;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.account.model.CreateAccountResponse;


@Service
public class GetAccountsService implements Query<Void, List<CreateAccountResponse>> {
	
	private final AccountRepository accountRepository;

	
	public GetAccountsService(AccountRepository accountRepository){
		this.accountRepository = accountRepository;
		
	}

	@Override
	public List<CreateAccountResponse> execute(Void input) {
		
		List<Account> list = accountRepository.findAll();
		List<CreateAccountResponse> responseList = new ArrayList<>();
		
		for(Account c : list) {
			CreateAccountResponse response = new CreateAccountResponse(c.getId(), c.getAccountNumber(), c.getBalance(), c.getStatus(), c.getCustomer().getId());
			responseList.add(response);
		}
		return responseList;
	}
	
}
