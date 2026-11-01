package com.example.banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;
import com.example.banksystem.customer.services.GetCustomersService;


public class GetCustomersServiceTest {

	
	@Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private GetCustomersService getCustomersService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

       
    }
    
   
    @Test
    public void given_customers_exists_when_get_customers_service_return_customer_dto() {
    	
    	
    	  Customer customer = new Customer();
          customer.setId(1);
          customer.setFirstName("Brandon");
          customer.setLastName("Smith");
          customer.setEmail("brandon@gmail.com");
          customer.setStatus("ACTIVE");
          customer.setCreatedAt(LocalDateTime.now());
          customer.setPasswordHash("treat");
          
          Customer customer2 = new Customer();
          customer2.setId(2);
          customer2.setFirstName("Billy");
          customer2.setLastName("Winchester");
          customer2.setEmail("billy@gmail.com");
          customer2.setStatus("ACTIVE");
          customer2.setCreatedAt(LocalDateTime.now());
          customer2.setPasswordHash("money");
          
          List<Customer> customers = List.of(customer, customer2);

  	    when(customerRepository.findAll()).thenReturn(customers);
  	    
  	    
  	    
  	  List<CreateCustomerResponse> response = getCustomersService.execute(null);
  	  
  	assertEquals("Brandon", response.get(0).getFirstName());
    assertEquals("Billy", response.get(1).getFirstName());
    
    verify(customerRepository, times(1)).findAll();

;
    	
    
    }


}
