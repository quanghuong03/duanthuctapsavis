package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Request.AddressRequest;
import com.example.sd_95_polo_store_be.Service.AddressService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/{id}")
    public Response<AddressRequest> addAddress(@RequestBody AddressRequest addressRequest, @PathVariable Integer id){
        return Response.ofSucceeded(addressService.addAddress(addressRequest,id));
    }
}
