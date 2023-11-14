package com.example.poloman.service;

import com.example.poloman.model.entity.Size;

import java.util.List;

public interface SizeService {

    List<Size> getAll();

    Size save(Size size);

    void delete(Integer masize);

}
