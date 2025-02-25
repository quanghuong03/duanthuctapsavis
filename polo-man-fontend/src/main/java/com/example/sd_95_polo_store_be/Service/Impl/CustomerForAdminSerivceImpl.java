package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Service.CustomerForAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerForAdminSerivceImpl implements CustomerForAdminService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerForAdminResponse> getAllByStatus() {
        return customerRepository.getCustomersForAdmin();
    }
}
