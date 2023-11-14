package com.example.poloman.service;

import com.example.poloman.model.entity.NhanVien;

import java.util.List;

public interface NhanVienService {

    List<NhanVien> getAll();

    NhanVien save(NhanVien nhanVien);

    void delete(Integer manhanvien);



}
