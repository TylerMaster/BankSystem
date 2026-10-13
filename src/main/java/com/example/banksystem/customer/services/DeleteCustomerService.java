package com.example.banksystem.customer.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.banksystem.Command;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.CustomerNotFoundException;

@Service
public class DeleteCustomerService implements Command<Integer, ResponseEntity<Void>> {

	public final CustomerRepository customerRepository;
	
	DeleteCustomerService(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
		
	}
	
	
	@Override
	public ResponseEntity<Void> execute(Integer input) {
		
		Optional<Customer> customer = customerRepository.findById(input);
		if(customer.isPresent()) {
			customerRepository.deleteById(input);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		throw new CustomerNotFoundException();
	}
}
