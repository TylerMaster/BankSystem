package com.example.banksystem.customer.model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerCommand {

	Integer Id;
	CreateCustomerRequest customer;
}
