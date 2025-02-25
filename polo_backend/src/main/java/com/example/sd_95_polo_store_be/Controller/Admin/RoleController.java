package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Role;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Service.RoleService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //    @GetMapping
//    public Response<List<Role>> gets() {
//        return Response.ofSucceeded(roleService.getAll());
//    }
    @GetMapping("")
    public Response<List<Role>> getsByStatus() {
        return Response.ofSucceeded(roleService.getColorByStatus());
    }
    @PostMapping("/add")
    public Response<Role> create(@RequestBody Role role) {
        try {
            roleService.saveRole(role);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Response<Role> update(@PathVariable Integer id, @RequestBody Role role) {
        try {
            role.setId(id);
            roleService.saveRole(role);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }
    @PutMapping("changeStatus/{id}")
    public Response<Void> changeBran(@PathVariable Integer id){
        roleService.changeStatus(id);
        return Response.ofSucceeded();
    }
    @GetMapping("/{id}")
    public Response<Role> get(@PathVariable Integer id) {
        return Response.ofSucceeded(roleService.getOne(id));
    }

}
