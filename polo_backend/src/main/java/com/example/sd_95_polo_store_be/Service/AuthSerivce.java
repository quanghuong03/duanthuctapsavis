package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Customers;
import com.example.sd_95_polo_store_be.Model.Request.LoginRequest;
import com.example.sd_95_polo_store_be.Model.Response.AdminResponse;
import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;

public interface AuthSerivce {
    AdminResponse adminLogin(LoginRequest request);
    CustomerResponse customerLogin(LoginRequest request);

    Customers signUpCustomers(Customers customers);
}
