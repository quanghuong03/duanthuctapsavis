package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse;
import com.example.sd_95_polo_store_be.Service.AdminService;
import com.example.sd_95_polo_store_be.Service.CustomerForAdminService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/customer")
public class AdminCustomerController {
    @Autowired
    private CustomerForAdminService customerForAdminService;


    @GetMapping("")
    public Response<List<CustomerForAdminResponse>> gets() {
        return Response.ofSucceeded(customerForAdminService.getAllByStatus());
    }

}
