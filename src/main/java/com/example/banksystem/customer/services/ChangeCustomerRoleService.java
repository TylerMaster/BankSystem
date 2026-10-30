package com.example.banksystem.customer.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.banksystem.TwoParamCommand;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.customer.model.CustomerRoles;
import com.example.banksystem.exception.CustomerNotFoundException;

@Service
public class ChangeCustomerRoleService implements TwoParamCommand<CustomerRoles, String> {

	
	private final CustomerRepository customerRepository;
	
	public ChangeCustomerRoleService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;

	}
	@Override
	public String execute(Integer id, CustomerRoles roles) {
		
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		
		if(optionalCustomer.isPresent()) {
		
		optionalCustomer.get().setRole(roles);
		
		customerRepository.save(optionalCustomer.get());
		
		return "Success";
		}
		throw new CustomerNotFoundException();
	}
	
	
}
