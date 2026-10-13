package com.example.banksystem.customer.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;


@Service
public class GetCustomersService implements Query<Void, List<CreateCustomerResponse>> {

public final CustomerRepository customerRepository;

public GetCustomersService(CustomerRepository customerRepository) {
	this.customerRepository = customerRepository;
	
}
@Override
public List<CreateCustomerResponse> execute(Void input) {
List<Customer> allCustomers = customerRepository.findAll();
List<CreateCustomerResponse> allCustomersDTOs = new ArrayList<>();
for (Customer c : allCustomers) {
	allCustomersDTOs.add(new CreateCustomerResponse(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getStatus()));
}
return allCustomersDTOs;
}
}
