package com.example.poloman.service.impl;

import com.example.poloman.model.entity.YeuThichChiTiet;
import com.example.poloman.repository.YeuThichChiTietRepository;
import com.example.poloman.service.YeuThichChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class YeuThichChiTietServiceImpl implements YeuThichChiTietService {

    @Autowired
    private YeuThichChiTietRepository yeuThichChiTietRepository;

    @Override
    public List<YeuThichChiTiet> getAll() {
        return yeuThichChiTietRepository.findAll();
    }

    @Override
    public YeuThichChiTiet save(YeuThichChiTiet yeuThichChiTiet) {
        return yeuThichChiTietRepository.save(yeuThichChiTiet);
    }

    @Override
    public void delete(UUID madanhsach) {
        yeuThichChiTietRepository.deleteById(madanhsach);
    }
}
