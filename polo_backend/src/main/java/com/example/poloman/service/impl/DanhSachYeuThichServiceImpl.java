package com.example.poloman.service.impl;

import com.example.poloman.model.entity.DanhSachYeuThich;
import com.example.poloman.repository.DanhSachYeuThichRepository;
import com.example.poloman.service.DanhSachYeuThichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DanhSachYeuThichServiceImpl implements DanhSachYeuThichService {

    @Autowired
    private DanhSachYeuThichRepository danhSachYeuThichRepository;

    @Override
    public List<DanhSachYeuThich> getAll() {
        return danhSachYeuThichRepository.findAll();
    }

    @Override
    public DanhSachYeuThich save(DanhSachYeuThich danhSachYeuThich) {
        return danhSachYeuThichRepository.save(danhSachYeuThich);
    }

    @Override
    public void delete(UUID madanhsach) {
        danhSachYeuThichRepository.deleteById(madanhsach);
    }
}
