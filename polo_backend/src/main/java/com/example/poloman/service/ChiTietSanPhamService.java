package com.example.poloman.service;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.reponse.SanPhamChiTietResponse;
import com.example.poloman.model.request.SanPhamChiTietRequest;

import java.util.List;

public interface ChiTietSanPhamService {

    List<ChiTietSanPham> getAll();

    ChiTietSanPham save(ChiTietSanPham chiTietSanPham);

    void delete(Integer mactsp);

    List<ChiTietSanPham> findOne(Integer masanpham);

    ChiTietSanPham createOrUpdate(SanPhamChiTietRequest sanPhamChiTietRequest, Integer masanpham);

    List<SanPhamChiTietResponse> getForSanPham(Integer masanpham);



}
