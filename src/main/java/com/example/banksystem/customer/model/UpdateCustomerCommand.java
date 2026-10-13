package com.example.banksystem.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerCommand {

	Integer Id;
	CreateCustomerRequest customer;
}
