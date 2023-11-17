package com.example.poloman.service;

import com.example.poloman.model.entity.ChatLieu;
import com.example.poloman.model.entity.MauSac;

import java.util.List;

public interface MauSacService {

    List<MauSac> getAll();

    MauSac save(MauSac mauSac);

    void delete(Integer mamausac);

    MauSac getOne(Integer mamausac);

}
