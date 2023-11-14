package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@Table(name = "khachhang")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer makhachhang;

    private String tenkhachhang;
    private Date ngaysinh;
    private String sodienthoai;
    private String diachi;
    private String email;
    private Boolean gioitinh;
    private String matkhau;

}
