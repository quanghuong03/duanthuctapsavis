package com.example.poloman.service.impl;

import com.example.poloman.model.entity.MauSac;
import com.example.poloman.model.entity.Size;
import com.example.poloman.repository.SizeRepository;
import com.example.poloman.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void delete(Integer masize) {
        sizeRepository.deleteById(masize);
    }

    @Override
    public Size getOne(Integer masize) {
        Optional<Size> optionalSize = sizeRepository.findById(masize);
        return optionalSize.get();
    }
}
