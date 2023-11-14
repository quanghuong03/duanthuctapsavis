package com.example.poloman.service.impl;

import com.example.poloman.model.entity.ChucVu;
import com.example.poloman.repository.ChucVuRepository;
import com.example.poloman.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public ChucVu save(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    @Override
    public void delete(Integer machucvu) {
        chucVuRepository.deleteById(machucvu);
    }
}
