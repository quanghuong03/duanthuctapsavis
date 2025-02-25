package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.AddressRequest;
import com.example.sd_95_polo_store_be.Model.Response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getForCustomer(Integer id);
    AddressRequest addAddress(AddressRequest addressRequest,Integer id);
}
