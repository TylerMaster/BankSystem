package com.example.banksystem.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banksystem.customer.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	boolean existsByEmail(String email);
	
	@Query("""
		    SELECT c FROM Customer c
		    WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
		       OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
		    """)
	List<Customer> findByFirstNameOrLastNameContaining(@Param("keyword") String name);
	
	Optional<Customer> findByEmail(String email);
}
