package com.example.poloman.service.impl;

import com.example.poloman.model.entity.DongSP;
import com.example.poloman.repository.DongSPRepository;
import com.example.poloman.service.DongSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DongSPServiceImpl implements DongSPService {

    @Autowired
    private DongSPRepository dongSPRepository;

    @Override
    public List<DongSP> getAll() {
        return dongSPRepository.findAll();
    }

    @Override
    public DongSP save(DongSP dongSP) {
        return dongSPRepository.save(dongSP);
    }

    @Override
    public void delete(Integer madongsp) {
        dongSPRepository.deleteById(madongsp);
    }
}
