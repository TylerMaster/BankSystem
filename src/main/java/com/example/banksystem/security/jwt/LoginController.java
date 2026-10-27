package com.example.banksystem.security.jwt;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.customer.model.CustomerLoginRequest;

@RestController

public class LoginController {

	
	private final AuthenticationManager manager;
	
	private final CustomerRepository customerRepository;
	
	
	
	public LoginController(AuthenticationManager manager, CustomerRepository customerRepository) {
		this.manager = manager;
		this.customerRepository = customerRepository;
		
	}
	@PutMapping("/testdisable/{customerId}")
	public ResponseEntity<String> testDisable(@PathVariable("customerId") Integer customerId){
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		customer.get().setStatus("ACTIVE");
		customerRepository.save(customer.get());
		return ResponseEntity.ok("success");
		
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody CustomerLoginRequest user){
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmail(),
				user.getPassword()
				
				);
		
		Authentication authentication = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(jwtToken);
	}
}
