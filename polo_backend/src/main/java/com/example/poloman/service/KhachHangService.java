package com.example.poloman.service;

import com.example.poloman.model.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll();

    KhachHang save(KhachHang khachHang);

    void delete(Integer makhachhang);

}
