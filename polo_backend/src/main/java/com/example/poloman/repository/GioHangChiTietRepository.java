package com.example.poloman.repository;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.entity.GioHang;
import com.example.poloman.model.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {
    Optional<GioHangChiTiet> findGioHangChiTietByGiohangAndSanpham(GioHang gioHang, ChiTietSanPham chiTietSanPham);
}
