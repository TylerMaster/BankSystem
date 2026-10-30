package com.example.banksystem.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.exception.CustomerNotFoundException;

@Service
public class CustomerDetailsService implements UserDetailsService {

	
	 private final CustomerRepository customerRepository;
	 
	 public CustomerDetailsService(CustomerRepository customerRepository) {
	        this.customerRepository = customerRepository;
	    }
	 
	  @Override
	  public UserDetails loadUserByUsername(String username) {
	        Customer customer = customerRepository.findByEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
	        
	        
	        return User.builder()
	                .username(customer.getEmail())
	                .password(customer.getPasswordHash())
	                .authorities("ROLE_" + customer.getRole().name())
	                .disabled(!customer.getStatus().equalsIgnoreCase("ACTIVE"))
	                .build();
	  }
}
