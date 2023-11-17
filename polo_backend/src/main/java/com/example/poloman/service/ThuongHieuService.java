package com.example.poloman.service;

import com.example.poloman.model.entity.Size;
import com.example.poloman.model.entity.ThuongHieu;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieu> getAll();

    ThuongHieu save(ThuongHieu thuongHieu);

    void delete(Integer mathuonghieu);

    ThuongHieu getOne(Integer mathuonghieu);

}
