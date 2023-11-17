package com.example.poloman.service;

import com.example.poloman.model.entity.DongSP;
import com.example.poloman.model.entity.Size;

import java.util.List;

public interface DongSPService {

    List<DongSP> getAll();

    DongSP save(DongSP dongSP);

    void delete(Integer madongsp);

    DongSP getOne(Integer madongsp);

}
