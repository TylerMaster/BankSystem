package com.example.banksystem.account.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateAccountRequest {

	private String status;
	@NotBlank(message = "Account number is required")
	@Size(max = 20, message = "Account number cannot exceed 20 characters")
	private String accountNumber;
	
	
}
