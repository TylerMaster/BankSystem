package com.example.banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.banksystem.customer.services.GetCustomerService;
import com.example.banksystem.exception.CustomerNotFoundException;
import com.example.banksystem.customer.CustomerRepository;
import com.example.banksystem.customer.model.CreateCustomerResponse;
import com.example.banksystem.customer.model.Customer;

public class GetCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private GetCustomerService getCustomerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("brandon@gmail.com");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void given_customer_exists_when_get_customer_service_return_customer_dto() {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Brandon");
        customer.setLastName("Smith");
        customer.setEmail("brandon@gmail.com");
        customer.setStatus("ACTIVE");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPasswordHash("treat");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        CreateCustomerResponse response = getCustomerService.execute(1);

        assertEquals(
            new CreateCustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getStatus()
            ),
            response
        );

        verify(customerRepository, times(1)).findById(1);
    }
    
    @Test
    public void given_customer_does_not_exist_when_get_customer_service_throw_customer_not_found_exception() {
    	
    	
    	 when(customerRepository.findById(1)).thenReturn(Optional.empty());
    	 
    	 assertThrows(CustomerNotFoundException.class, () -> getCustomerService.execute(1));
    	
    	 verify(customerRepository, times(1)).findById(1);
    }
}