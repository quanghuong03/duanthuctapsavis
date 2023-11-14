package com.example.poloman.service;

import com.example.poloman.model.entity.YeuThichChiTiet;

import java.util.List;
import java.util.UUID;

public interface YeuThichChiTietService {

    List<YeuThichChiTiet> getAll();

    YeuThichChiTiet save(YeuThichChiTiet yeuThichChiTiet);

    void delete(UUID madanhsach);

}
