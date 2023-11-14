package com.example.poloman.service;

import com.example.poloman.model.entity.DongSP;

import java.util.List;

public interface DongSPService {

    List<DongSP> getAll();

    DongSP save(DongSP dongSP);

    void delete(Integer madongsp);

}
