package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Request.CheckAvailabilityRequest;
import com.example.sd_95_polo_store_be.Model.Response.CheckAvailabilityResponse;
import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import com.example.sd_95_polo_store_be.Service.CustomerService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public Response<CustomerResponse> getOne(@PathVariable Integer id){
        return Response.ofSucceeded(customerService.getOne(id));
    }

    @PostMapping(value = "/checkEmail", consumes = "application/json")
    public CheckAvailabilityResponse checkEmailAvailability(@RequestBody CheckAvailabilityRequest request) {
        boolean available = customerService.isEmailAvailable(request.getEmail());
        return new CheckAvailabilityResponse(available);
    }

    @PostMapping(value = "/checkPhone", consumes = "application/json")
    public CheckAvailabilityResponse checkPhoneAvailability(@RequestBody CheckAvailabilityRequest request) {
        boolean available = customerService.isPhoneAvailable(request.getPhone());
        return new CheckAvailabilityResponse(available);
    }

}
