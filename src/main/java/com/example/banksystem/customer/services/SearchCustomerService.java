package com.example.banksystem.customer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;

@Service
public class SearchCustomerService implements Query<String, List<CreateCustomerResponse>>{

	
	private final CustomerRepository customerRepository;
	
	SearchCustomerService(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
		
	}
	
	@Override
	public List<CreateCustomerResponse> execute(String name) {
		List<Customer> list = customerRepository.findByFirstNameOrLastNameContaining(name);
		List<CreateCustomerResponse> allCustomersDTOs = new ArrayList<>();
		for (Customer c : list) {
			allCustomersDTOs.add(new CreateCustomerResponse(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getStatus()));
		}
		return allCustomersDTOs;
	}
	
	
}
