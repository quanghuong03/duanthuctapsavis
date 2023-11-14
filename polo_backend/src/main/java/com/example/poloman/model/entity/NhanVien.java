package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "nhanvien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer manhanvien;

    private String tennhanvien;
    private Date ngaysinh;
    private String sodienthoai;
    private String diachi;
    private String email;
    private Boolean gioitinh;
    private String matkhau;

    @ManyToOne
    @JoinColumn(name = "chucvu")
    private ChucVu chucvu;

}
