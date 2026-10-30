package com.example.banksystem.customer.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banksystem.Command;
import com.example.banksystem.account.model.Account;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.customer.model.CustomerStatus;
import com.example.banksystem.exception.CustomerNotFoundException;

@Service
@Transactional
public class DeleteCustomerService implements Command<Integer, ResponseEntity<Void>> {

	public final CustomerRepository customerRepository;
	
	DeleteCustomerService(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
		
	}
	
	
	@Override
	public ResponseEntity<Void> execute(Integer input) {
		
		Optional<Customer> customer = customerRepository.findById(input);
		if(customer.isPresent()) {
			
			 if (!customer.get().getAccounts().isEmpty()) {
				 	
				 System.out.println("Customer: " + customer.get().getEmail());
				 System.out.println("Accounts found: " + customer.get().getAccounts().size());
				 
				 
			        customer.get().setStatus(CustomerStatus.DISABLED.getMessage());

			        for (Account account : customer.get().getAccounts()) {
			        	 System.out.println("Disabling account: " + account.getId());
			        	
			            account.setStatus(CustomerStatus.DISABLED.getMessage());
			        }

			        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			    }
			
			
			
			customerRepository.deleteById(input);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		throw new CustomerNotFoundException();
	}
}
