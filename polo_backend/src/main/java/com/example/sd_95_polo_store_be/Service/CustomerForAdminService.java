package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse;

import java.util.List;

public interface CustomerForAdminService {
    List<CustomerForAdminResponse> getAllByStatus();
}
