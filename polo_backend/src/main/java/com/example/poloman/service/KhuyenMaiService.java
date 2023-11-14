package com.example.poloman.service;

import com.example.poloman.model.entity.KhuyenMai;

import java.util.List;
import java.util.UUID;

public interface KhuyenMaiService {

    List<KhuyenMai> getAll();

    KhuyenMai save(KhuyenMai khuyenMai);

    void delete(UUID id);

}
