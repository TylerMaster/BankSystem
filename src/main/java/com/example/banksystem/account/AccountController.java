package com.example.banksystem.account;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banksystem.account.model.CreateAccountRequest;
import com.example.banksystem.account.model.CreateAccountResponse;
import com.example.banksystem.account.model.DepositDTO;
import com.example.banksystem.account.model.RetrieveAccountEntryResponse;
import com.example.banksystem.account.model.TransferDTO;
import com.example.banksystem.account.model.UpdateAccountCommand;
import com.example.banksystem.account.services.CreateAccountService;
import com.example.banksystem.account.services.DepositService;
import com.example.banksystem.account.services.GetAccountEntriesService;
import com.example.banksystem.account.services.GetAccountService;
import com.example.banksystem.account.services.GetAccountsService;
import com.example.banksystem.account.services.GetAllCustomerAccountsService;
import com.example.banksystem.account.services.TransferService;
import com.example.banksystem.account.services.UpdateAccountService;
import com.example.banksystem.account.services.WithdrawalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

	private final CreateAccountService createAccountService;
	private final GetAccountService getAccountService;
	private final GetAccountsService getAccountsService;
	private final GetAllCustomerAccountsService getAllCustomerAccountsService;
	private final DepositService depositService;
	private final TransferService transferService;
	private final WithdrawalService withdrawalService;
	private final GetAccountEntriesService getAccountEntriesService;
	private final UpdateAccountService updateAccountService;
	
	public AccountController(CreateAccountService createAccountService,
							 GetAccountService getAccountService,
							 GetAccountsService getAccountsService,
							 GetAllCustomerAccountsService getAllCustomerAccountsService,
							 DepositService depositService,
							 TransferService transferService,
							 WithdrawalService withdrawalService,
							 GetAccountEntriesService getAccountEntriesService,
							 UpdateAccountService updateAccountService) {
		
		this.createAccountService = createAccountService;
		this.getAccountService = getAccountService;
		this.getAccountsService = getAccountsService;
		this.getAllCustomerAccountsService = getAllCustomerAccountsService;
		this.depositService = depositService;
		this.transferService = transferService;
		this.withdrawalService = withdrawalService;
		this.getAccountEntriesService = getAccountEntriesService;
		this.updateAccountService = updateAccountService;
	}
	
	
	@PostMapping("/customers/{customerId}/accounts")
	public ResponseEntity<CreateAccountResponse> createAccount(
			@PathVariable("customerId") Integer customerId,
			@Valid @RequestBody CreateAccountRequest request){
		
		 CreateAccountResponse response =
	                createAccountService.execute(customerId, request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PostMapping("/accounts/{accountId}/deposits")
			public ResponseEntity<String> deposit(@PathVariable("accountId") Integer accountId,
			        @Valid @RequestBody DepositDTO request) {
		
		return ResponseEntity.ok(depositService.execute(accountId, request));
	}
	@PostMapping("/accounts/{accountId}/withdrawals")
	public ResponseEntity<String> withdraw(@PathVariable("accountId") Integer accountId,
			@Valid @RequestBody DepositDTO request){
		
		return ResponseEntity.ok(withdrawalService.execute(accountId, request));
	}
	@PostMapping("/accounts/{accountId}/transfers")
	public ResponseEntity<String> transfer(@PathVariable("accountId") Integer accountId, 
					@Valid @RequestBody TransferDTO request){
		return ResponseEntity.ok(transferService.execute(accountId, request));
	}
	
	@GetMapping("/accounts/{accountId}/accountentries")
	public ResponseEntity<List<RetrieveAccountEntryResponse>> getAccountEntries(@PathVariable("accountId") Integer accountId){
		
		return ResponseEntity.ok(getAccountEntriesService.execute(accountId));
	}
	
	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<CreateAccountResponse> getAccount(@PathVariable("accountId") Integer accountId){
		
		
		
		return ResponseEntity.ok(getAccountService.execute(accountId));
	}
	@GetMapping("/admin/accounts")
	public ResponseEntity<List<CreateAccountResponse>> getAllAccounts(){
		return ResponseEntity.ok(getAccountsService.execute(null));
	}
	@GetMapping("/customers/{customerId}/accounts")
	public ResponseEntity<List<CreateAccountResponse>> getAllCustomerAccounts(@PathVariable("customerId") Integer customerId){
		return ResponseEntity.ok(getAllCustomerAccountsService.execute(customerId));
	}
	@PutMapping("admin/accounts/{accountId}")
	public ResponseEntity<CreateAccountResponse> updateAccount(@PathVariable("accountId") Integer id, @RequestBody CreateAccountRequest request){
		
		return ResponseEntity.ok(updateAccountService.execute(id, new UpdateAccountCommand(id, request)));
	}
}
