package com.example.banksystem.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banksystem.account.model.CreateAccountRequest;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.account.services.CreateAccountService;
import com.example.banksystem.account.services.GetAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

	private final CreateAccountService createAccountService;
	private final GetAccountService getAccountService;
	
	public AccountController(CreateAccountService createAccountService, GetAccountService getAccountService) {
		
		this.createAccountService = createAccountService;
		this.getAccountService = getAccountService;
	}
	
	
	@PostMapping("/customers/{customerId}/accounts")
	public ResponseEntity<CreateAccountResponse> createAccount(
			@PathVariable("customerId") Integer customerId,
			@Valid @RequestBody CreateAccountRequest request){
		
		 CreateAccountResponse response =
	                createAccountService.execute(customerId, request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<CreateAccountResponse> getAccount(@PathVariable("accountId") Integer accountId){
		
		
		
		return ResponseEntity.ok(getAccountService.execute(accountId));
	}
}
