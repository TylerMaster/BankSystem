package com.example.banksystem.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banksystem.customer.model.CreateCustomerRequest;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.services.CreateCustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CreateCustomerService createCustomerService;

    public CustomerController(CreateCustomerService createCustomerService) {
        this.createCustomerService = createCustomerService;
    }
    
    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(
    		@RequestBody CreateCustomerRequest request){
    	
    	CreateCustomerResponse response = 
    			createCustomerService.execute(request);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
