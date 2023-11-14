package com.example.poloman.service;

import com.example.poloman.model.entity.HoaDonChiTiet;
import com.example.poloman.model.request.TaoHoaDonChiTietRequset;

import java.util.List;

public interface HoaDonChiTietService {

    List<HoaDonChiTiet> getAll();

    HoaDonChiTiet save(HoaDonChiTiet hoaDonChiTiet);

    void delete(Integer mahoadon);

    HoaDonChiTiet create(TaoHoaDonChiTietRequset hoaDonChiTietRequset, Integer maHoaDon);

    HoaDonChiTiet getByMaHoaDon(Integer mahoadon);

}
