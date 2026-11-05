package com.example.banksystem.security.jwt;

import com.example.banksystem.Query;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.customer.model.CustomerLoginRequest;
import com.example.banksystem.customer.model.JwtCustomerResponse;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements Query<CustomerLoginRequest, JwtCustomerResponse> {

	
	private final CustomerRepository customerRepository;
	
	private final AuthenticationManager manager;
	
	public LoginService(CustomerRepository customerRepository, AuthenticationManager manager) {
		this.customerRepository = customerRepository;
		this.manager = manager;
		
	}
	@Override
	public JwtCustomerResponse execute(CustomerLoginRequest user) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmail(),
				user.getPassword()
				
				);
		
		Authentication authentication = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());
		
		Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
		
		return new JwtCustomerResponse(jwtToken, customer.get().getId(), customer.get().getEmail(), customer.get().getFirstName(), customer.get().getLastName(),customer.get().getRole().name());
	}
}
