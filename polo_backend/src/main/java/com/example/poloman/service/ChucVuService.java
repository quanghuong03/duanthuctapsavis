package com.example.poloman.service;

import com.example.poloman.model.entity.ChucVu;

import java.util.List;

public interface ChucVuService {

    List<ChucVu> getAll();

    ChucVu save(ChucVu chucVu);

    void delete(Integer machucvu);

}
