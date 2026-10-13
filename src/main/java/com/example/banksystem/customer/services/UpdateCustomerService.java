package com.example.banksystem.customer.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.Command;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerRequest;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.customer.model.UpdateCustomerCommand;
import com.example.banksystem.exception.CustomerNotFoundException;
import com.example.banksystem.exception.DuplicateCustomerEmailException;

@Service
public class UpdateCustomerService implements Command<UpdateCustomerCommand, CreateCustomerResponse> {

	private final CustomerRepository customerRepository;
	
	public UpdateCustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		
	}
	
	
	@Override
	public CreateCustomerResponse execute(UpdateCustomerCommand updateCustomerCommand) {
		
		Optional<Customer> customerOptional = customerRepository.findById(updateCustomerCommand.getId());
		
		
		if (customerRepository.existsByEmail(updateCustomerCommand.getCustomer().getEmail())) {
		    throw new DuplicateCustomerEmailException();
		}
		
		if(customerOptional.isPresent()) {
			
	
			Customer customer = new Customer();
			customer.setId(updateCustomerCommand.getId());
			customer.setFirstName(updateCustomerCommand.getCustomer().getFirstName());
			customer.setLastName(updateCustomerCommand.getCustomer().getLastName());
			customer.setEmail(updateCustomerCommand.getCustomer().getEmail());
			customer.setStatus(customerOptional.get().getStatus());
			customer.setCreatedAt(customerOptional.get().getCreatedAt());
			customerRepository.save(customer);
			
			CreateCustomerResponse createCustomerResponse = new CreateCustomerResponse();
			createCustomerResponse.setId(customer.getId());
			createCustomerResponse.setFirstName(customer.getFirstName());
			createCustomerResponse.setLastName(customer.getLastName());
			createCustomerResponse.setEmail(customer.getEmail());
			createCustomerResponse.setStatus(customer.getStatus());
			
			return createCustomerResponse;
		}
		throw new CustomerNotFoundException();
	}
}
