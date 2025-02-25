package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();

    List<Role> getColorByStatus();

    Role saveRole(Role role);

    Role getOne(Integer id);

    Role findById(Integer id);
    public void changeStatus(Integer id);
    boolean isColorDataDuplicate(Role role);
}
