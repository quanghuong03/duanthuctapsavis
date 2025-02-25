package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Admins;
import com.example.sd_95_polo_store_be.Repository.AdminRepository;
import com.example.sd_95_polo_store_be.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public List<Admins> getAll() {
        return adminRepository.findAll();
    }
}
