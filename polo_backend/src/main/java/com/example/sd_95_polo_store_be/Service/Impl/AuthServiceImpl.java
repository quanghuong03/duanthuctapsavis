package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Admins;
import com.example.sd_95_polo_store_be.Model.Entity.Customers;
import com.example.sd_95_polo_store_be.Model.Request.LoginRequest;
import com.example.sd_95_polo_store_be.Model.Response.AdminResponse;
import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import com.example.sd_95_polo_store_be.Repository.AdminRepository;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Service.AuthSerivce;
import com.example.sd_95_polo_store_be.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class AuthServiceImpl implements AuthSerivce {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public AdminResponse adminLogin(LoginRequest request) {
        Admins admins = adminRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return generateAdmin(admins);
    }

    @Override
    public CustomerResponse customerLogin(LoginRequest request) {
        Customers customers = customerRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        System.out.println(customers.getId());
        return customerService.getOne(customers.getId());
    }

    @Override
    public Customers signUpCustomers(Customers customers) {
        var now = OffsetDateTime.now();
        Customers newCustomers = new Customers();
        newCustomers.setName(customers.getName());
        newCustomers.setEmail(customers.getEmail());
        newCustomers.setPhone(customers.getPhone());
        newCustomers.setAvatar(customers.getAvatar());
        newCustomers.setPassword(customers.getPassword());
        newCustomers.setStatus("1");
        newCustomers.setCreatedAt(now);
        newCustomers.setUpdatedAt(now);
        customerRepository.save(newCustomers);
        return newCustomers;
    }


    private AdminResponse generateAdmin(Admins admins) {
        return new AdminResponse().setId(admins.getId()).
                setNameAdmin(admins.getName())
                .setEmail(admins.getEmail())
                .setPhone(admins.getPhone())
                .setAvatar(admins.getAvatar())
                .setAddress(admins.getAddress())
                .setStatus(admins.getStatus())
                .setRoleId(admins.getRole().getId())
                .setNameRole(admins.getRole().getName())
                ;
    }
}
