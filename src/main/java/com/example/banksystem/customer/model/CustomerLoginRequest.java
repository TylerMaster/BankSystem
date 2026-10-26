package com.example.banksystem.customer.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerLoginRequest {


	@NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
	
	@NotBlank(message = "Password is required")
    private String password;
	
	//@Column(name = "status", nullable = false)
	//private String status;
}
