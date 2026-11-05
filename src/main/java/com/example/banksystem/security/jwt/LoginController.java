package com.example.banksystem.security.jwt;




import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.example.banksystem.customer.model.CustomerLoginRequest;
import com.example.banksystem.customer.model.JwtCustomerResponse;

@RestController

public class LoginController {

	
	private final LoginService loginService;
	
	
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
		
	}
	/*@PutMapping("/testdisable/{customerId}")
	public ResponseEntity<String> testDisable(@PathVariable("customerId") Integer customerId){
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		customer.get().setStatus("ACTIVE");
		customerRepository.save(customer.get());
		return ResponseEntity.ok("success");
		
	}*/
	@PostMapping("/login")
	public ResponseEntity<JwtCustomerResponse> login(@RequestBody CustomerLoginRequest user){
		
	
		return ResponseEntity.ok(loginService.execute(user));
	}
}
