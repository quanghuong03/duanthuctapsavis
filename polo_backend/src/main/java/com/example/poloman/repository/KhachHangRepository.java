package com.example.poloman.repository;

import com.example.poloman.model.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    KhachHang findByEmailAndMatkhau(String email, String matkhau);
}
