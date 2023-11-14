package com.example.poloman.service.impl;

import com.example.poloman.model.entity.KhachHang;
import com.example.poloman.repository.KhachHangRepository;
import com.example.poloman.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang save(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(Integer makhachhang) {
        khachHangRepository.deleteById(makhachhang);
    }
}
