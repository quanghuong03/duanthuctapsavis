package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "hoadonchitiet")
public class HoaDonChiTiet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mahoadon;

    private Integer soluong;
    private Double dongia;
    private Integer trangthai;

    @ManyToOne
    @JoinColumn(name = "hoadon")
    private HoaDon hoadon;

    @ManyToOne
    @JoinColumn(name = "sanpham")
    private ChiTietSanPham chiTietSanPham;

}
