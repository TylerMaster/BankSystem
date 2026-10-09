package com.example.banksystem.customer.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.banksystem.Command;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerRequest;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.DuplicateCustomerEmailException;
@Service
public class CreateCustomerService implements Command<CreateCustomerRequest, CreateCustomerResponse>{

	
	private final CustomerRepository customerRepository;
	
	public CreateCustomerService(CustomerRepository customerRepository) {
	    this.customerRepository = customerRepository;
	}
	
	@Override
    public CreateCustomerResponse execute(CreateCustomerRequest input) {
		
		
		if (customerRepository.existsByEmail(input.getEmail())) {
		    throw new DuplicateCustomerEmailException();
		}
		Customer customer = new Customer();
	    customer.setFirstName(input.getFirstName());
	    customer.setLastName(input.getLastName());
	    customer.setEmail(input.getEmail());
	    customer.setStatus("ACTIVE");
	    customer.setCreatedAt(LocalDateTime.now());

	    Customer savedCustomer = customerRepository.save(customer);

	    CreateCustomerResponse response = new CreateCustomerResponse();
	    response.setId(savedCustomer.getId());
	    response.setFirstName(savedCustomer.getFirstName());
	    response.setLastName(savedCustomer.getLastName());
	    response.setEmail(savedCustomer.getEmail());
	    response.setStatus(savedCustomer.getStatus());

	    return response;
    }
}
