package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "danhsachyeuthich")
public class DanhSachYeuThich {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID madanhsach;

    private Date ngaytao;
    private Date ngaycapnhap;
    private String ghichu;
    private Integer trangthai;

    @ManyToOne
    @JoinColumn(name = "nguoisohuu")
    private KhachHang nguoisohuu;

}
