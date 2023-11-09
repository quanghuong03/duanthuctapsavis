package com.n10savis.poloman.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKhachHang;

    private String tenKhachHang;

    private Date ngaySinh;

    private String soDienThoai;

    private String diaChi;

    private String email;

    private Integer gioiTinh;

    private String matKhau;
}
