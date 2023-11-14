package com.example.poloman.service.impl;

import com.example.poloman.model.entity.NhanVien;
import com.example.poloman.repository.NhanVienRepository;
import com.example.poloman.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien save(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public void delete(Integer manhanvien) {
        nhanVienRepository.deleteById(manhanvien);
    }


}
