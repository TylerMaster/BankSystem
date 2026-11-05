package com.example.banksystem.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtCustomerResponse {
    private String token;
    private Integer customerId;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
