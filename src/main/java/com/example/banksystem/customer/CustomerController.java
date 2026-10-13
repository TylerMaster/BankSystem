package com.example.banksystem.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.banksystem.customer.model.CreateCustomerRequest;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.UpdateCustomerCommand;
import com.example.banksystem.customer.services.CreateCustomerService;
import com.example.banksystem.customer.services.DeleteCustomerService;
import com.example.banksystem.customer.services.GetCustomerService;
import com.example.banksystem.customer.services.GetCustomersService;
import com.example.banksystem.customer.services.SearchCustomerService;
import com.example.banksystem.customer.services.UpdateCustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CreateCustomerService createCustomerService;
	private final GetCustomerService getCustomerService;
	private final GetCustomersService getCustomersService;
	private final UpdateCustomerService updateCustomerService;
	private final DeleteCustomerService deleteCustomerService;
	private final SearchCustomerService searchCustomerService;

    public CustomerController(CreateCustomerService createCustomerService, GetCustomerService getCustomerService, GetCustomersService getCustomersService, UpdateCustomerService updateCustomerService, DeleteCustomerService deleteCustomerService, SearchCustomerService searchCustomerService) {
        this.createCustomerService = createCustomerService;
        this.getCustomerService = getCustomerService;
        this.getCustomersService = getCustomersService;
        this.updateCustomerService = updateCustomerService;
        this.deleteCustomerService = deleteCustomerService;
        this.searchCustomerService = searchCustomerService;
    }
    
    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(
    		@Valid @RequestBody CreateCustomerRequest request){
    	
    	CreateCustomerResponse response = 
    			createCustomerService.execute(request);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<CreateCustomerResponse>> getCustomers(){
    	return ResponseEntity.ok(getCustomersService.execute(null));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerResponse> getCustomer(
    		@PathVariable("id") Integer id){
    	
    	return ResponseEntity.ok(getCustomerService.execute(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<CreateCustomerResponse>> searchCustomer(@RequestParam("name") String name){
    	return ResponseEntity.ok(searchCustomerService.execute(name));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CreateCustomerResponse> updateCustomer(
    		 @PathVariable("id") Integer id, @Valid @RequestBody CreateCustomerRequest createCustomerRequest){
    	
    	return ResponseEntity.ok(updateCustomerService.execute(new UpdateCustomerCommand(id, createCustomerRequest)));
    	
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
    		@PathVariable("id") Integer id){
    	
    	return deleteCustomerService.execute(id);
    }
    
}
