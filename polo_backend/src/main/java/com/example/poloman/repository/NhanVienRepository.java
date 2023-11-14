package com.example.poloman.repository;

import com.example.poloman.model.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    NhanVien findByEmailAndMatkhau(String email,String matkhau);
}
