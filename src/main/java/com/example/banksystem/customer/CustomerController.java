package com.example.banksystem.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banksystem.customer.model.CreateCustomerRequest;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.services.CreateCustomerService;
import com.example.banksystem.customer.services.GetCustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CreateCustomerService createCustomerService;
	private final GetCustomerService getCustomerService;

    public CustomerController(CreateCustomerService createCustomerService, GetCustomerService getCustomerService) {
        this.createCustomerService = createCustomerService;
        this.getCustomerService = getCustomerService;
    }
    
    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(
    		@Valid @RequestBody CreateCustomerRequest request){
    	
    	CreateCustomerResponse response = 
    			createCustomerService.execute(request);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerResponse> getCustomer(
    		@PathVariable("id") Integer id){
    	
    	return ResponseEntity.ok(getCustomerService.execute(id));
    }
}
