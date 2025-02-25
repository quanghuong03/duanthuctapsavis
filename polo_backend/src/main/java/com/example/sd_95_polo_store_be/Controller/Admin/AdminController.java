package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Admins;
import com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse;
import com.example.sd_95_polo_store_be.Service.AdminService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/manger")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public Response<List<Admins>> gets() {
        return Response.ofSucceeded(adminService.getAll());
    }
}
