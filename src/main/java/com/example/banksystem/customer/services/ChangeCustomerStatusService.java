package com.example.banksystem.customer.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.ChangeCustomerStatusRequest;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.CustomerNotFoundException;

@Service
public class ChangeCustomerStatusService implements TwoParamCommand<ChangeCustomerStatusRequest, String> {

	private final CustomerRepository customerRepository;
	
	public ChangeCustomerStatusService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		
	}
	@Override
	public String execute(Integer id, ChangeCustomerStatusRequest request) {
		
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if(optionalCustomer.isPresent()) {
			optionalCustomer.get().setStatus(request.getStatus());
			
			customerRepository.save(optionalCustomer.get());
			
			return "Success";
			
		}
		
		throw new CustomerNotFoundException();
	}
}
