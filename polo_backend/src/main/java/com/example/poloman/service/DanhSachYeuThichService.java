package com.example.poloman.service;

import com.example.poloman.model.entity.DanhSachYeuThich;

import java.util.List;
import java.util.UUID;

public interface DanhSachYeuThichService {

    List<DanhSachYeuThich> getAll();

    DanhSachYeuThich save(DanhSachYeuThich danhSachYeuThich);

    void delete(UUID madanhsach);

}
