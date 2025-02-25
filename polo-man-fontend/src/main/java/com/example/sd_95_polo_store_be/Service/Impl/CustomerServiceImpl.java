package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Service.AddressService;
import com.example.sd_95_polo_store_be.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressService addressService;

    @Override
    public CustomerResponse getOne(Integer id) {
        var customer = customerRepository.getOneCustomer(id).orElseThrow();
        customer.setAddress(addressService.getForCustomer(id));
        return customer;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !customerRepository.existsByEmail(email);
    }

    @Override
    public boolean isPhoneAvailable(String phone) {
        return !customerRepository.existsByPhone(phone);
    }
}
