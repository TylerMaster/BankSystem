package com.example.banksystem.customer.services;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.CustomerNotFoundException;

import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.banksystem.Query;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
@Service
public class GetCustomerService implements Query<Integer, CreateCustomerResponse> {
	private final  CustomerRepository customerRepository;
	
	public GetCustomerService(CustomerRepository customerRepository) {
		
		this.customerRepository = customerRepository;
	}
	
	@Override
	public CreateCustomerResponse execute(Integer input) {
		
		Optional<Customer> customerOptional = customerRepository.findById(input);
		if(customerOptional.isPresent()) {
			
			Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();
		  
		  String authenticatedEmail = authentication.getName();
		  
		  if(!customerOptional.get().getEmail()
				  .equalsIgnoreCase(authenticatedEmail)) {
			  
			  throw new AccessDeniedException(
					  "You are not authorized to access this user's accounts");
		  }
			Customer customer = customerOptional.get();
			
			  CreateCustomerResponse response = new CreateCustomerResponse();
		        response.setId(customer.getId());
		        response.setFirstName(customer.getFirstName());
		        response.setLastName(customer.getLastName());
		        response.setEmail(customer.getEmail());
		        response.setStatus(customer.getStatus());
		        

		        return response;
		}
		throw new CustomerNotFoundException();
	}
	
}
