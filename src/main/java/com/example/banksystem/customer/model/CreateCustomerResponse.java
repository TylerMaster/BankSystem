package com.example.banksystem.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    
}
