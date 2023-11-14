package com.example.poloman.service;

import com.example.poloman.model.entity.SanPham;
import com.example.poloman.model.reponse.SanPhamChiTietResponse;
import com.example.poloman.model.reponse.SanPhamOverviewResponse;
import com.example.poloman.model.reponse.SanPhamResponse;
import com.example.poloman.model.request.SanPhamRequest;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {

    List<SanPham> getAll();

    List<SanPhamOverviewResponse> getAllSanPham();

    SanPham save(SanPham sanPham);

    void delete(Integer masanpham);

    SanPhamResponse getOne(Integer masanpham);

    void create(SanPhamRequest sanPhamRequest);
    void update(Integer masanpham, SanPhamRequest sanPhamRequest);



}
