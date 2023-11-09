package com.n10savis.poloman.repository;

import com.n10savis.poloman.model.entity.KhachHang;
import com.n10savis.poloman.model.response.KhachHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    @Query(value = "select new com.n10savis.poloman.model.response.KhachHangResponse(kh.maKhachHang,kh.tenKhachHang,kh.ngaySinh,kh.soDienThoai,kh.diaChi,kh.email,kh.gioiTinh) from KhachHang kh")
    List<KhachHangResponse> getAll();
}
