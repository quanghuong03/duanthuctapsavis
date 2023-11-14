package com.example.poloman.service;

import com.example.poloman.model.entity.GioHang;
import com.example.poloman.model.reponse.GioHangResponse;
import com.example.poloman.model.request.CreateGioHangRequest;

import java.util.List;

public interface GioHangService {

    List<GioHang> getAll();

    GioHang save(GioHang gioHang);

    void delete(Integer magiohang);

    GioHangResponse getOne(Integer makhachhang);

    GioHangResponse getOneByTrangThai(Integer makhachhang);


}
