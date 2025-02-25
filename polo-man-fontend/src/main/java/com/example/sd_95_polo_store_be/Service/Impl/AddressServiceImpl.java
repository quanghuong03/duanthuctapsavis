package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Address;
import com.example.sd_95_polo_store_be.Model.Entity.CustomerAddress;
import com.example.sd_95_polo_store_be.Model.Request.AddressRequest;
import com.example.sd_95_polo_store_be.Model.Response.AddressResponse;
import com.example.sd_95_polo_store_be.Repository.AddressRepository;
import com.example.sd_95_polo_store_be.Repository.AddressRepositoryA;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressRepositoryA addressRepositoryA;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<AddressResponse> getForCustomer(Integer id) {
        List<AddressResponse> addressResponses = addressRepository.getByProductId(id);
        return addressResponses;
    }

    @Override
    public AddressRequest addAddress(AddressRequest addressRequest, Integer id) {
        var now = OffsetDateTime.now();
        var customer = customerRepository.findById(id).orElseThrow();
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setDistrict(addressRequest.getDistrict());
        address.setWard(addressRequest.getWard());
        address.setCreatedAt(now);
        address.setUpdatedAt(now);
        address.setStatus(1);
        address.setFullAddress(addressRequest.getFullAddress());
        addressRepositoryA.save(address);
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setAddress(address);
        customerAddress.setCustomers(customer);
        addressRepository.save(customerAddress);
        return gerateAddress(address);
    }

    private AddressRequest gerateAddress(Address address){
        return new AddressRequest().setId(address.getId()).setCity(address.getCity()).setDistrict(address.getDistrict()).setWard(address.getWard()).setFullAddress(address.getFullAddress());
    }
}
