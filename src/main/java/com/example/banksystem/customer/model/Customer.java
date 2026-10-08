package com.example.banksystem.customer.model;
import java.util.ArrayList;
import java.util.List;
import com.example.banksystem.account.model.Account;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String status;
	
	private java.time.LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();
	
	
}
