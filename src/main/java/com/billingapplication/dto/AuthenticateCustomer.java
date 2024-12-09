package com.billingapplication.dto;

import lombok.Data;

@Data
public class AuthenticateCustomer {
    private String name;
    private String pass;
    private String email;
    //private Boolean isAuthenticated;
}
