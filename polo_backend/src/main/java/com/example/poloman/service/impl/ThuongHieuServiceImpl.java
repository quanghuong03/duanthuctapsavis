package com.example.poloman.service.impl;

import com.example.poloman.model.entity.ThuongHieu;
import com.example.poloman.repository.ThuongHieuRepository;
import com.example.poloman.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;


    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu save(ThuongHieu thuongHieu) {
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public void delete(Integer mathuonghieu) {
        thuongHieuRepository.deleteById(mathuonghieu);
    }
}
