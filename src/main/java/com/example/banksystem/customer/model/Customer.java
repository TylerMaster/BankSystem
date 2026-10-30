package com.example.banksystem.customer.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.banksystem.account.model.Account;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private Integer id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private CustomerRoles role = CustomerRoles.CUSTOMER;
	
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();
	
	
}
