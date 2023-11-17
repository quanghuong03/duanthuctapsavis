package com.example.poloman.service.impl;

import com.example.poloman.model.entity.ChatLieu;
import com.example.poloman.model.entity.MauSac;
import com.example.poloman.repository.MauSacRepository;
import com.example.poloman.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac save(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    @Override
    public void delete(Integer mamausac) {
        mauSacRepository.deleteById(mamausac);
    }

    @Override
    public MauSac getOne(Integer mamausac) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(mamausac);
        return optionalMauSac.get();
    }
}
