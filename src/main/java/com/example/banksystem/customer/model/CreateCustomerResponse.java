package com.example.banksystem.customer.model;

import lombok.Data;

@Data
public class CreateCustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
}
