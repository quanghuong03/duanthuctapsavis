package com.example.poloman.service;

import com.example.poloman.model.entity.GioHangChiTiet;
import com.example.poloman.model.request.CreateGioHangRequest;

import com.example.poloman.model.request.UpdateSoLuongGioHang;
import com.example.poloman.model.request.UpdateTrangThaiGioHang;

import java.util.List;

public interface GioHangChiTietService {

    List<GioHangChiTiet> getAll();

    GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);

    void delete(Integer magiohang);
    void add(CreateGioHangRequest request,Integer makhachhang);

    void updateTinhTrang(Integer magiohang, UpdateTrangThaiGioHang trangThaiGioHang);

    void updatesoluong(Integer magiohang, UpdateSoLuongGioHang soLuongGioHang);
}
