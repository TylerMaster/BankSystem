package com.example.banksystem.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banksystem.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	boolean existsByEmail(String email);
}
