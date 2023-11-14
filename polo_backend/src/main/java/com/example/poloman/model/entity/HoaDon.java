package com.example.poloman.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "hoadon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mahoadon;


    private OffsetDateTime ngaytao;
    private Integer trangthai;
    private String ghichu;
    private OffsetDateTime ngaythanhtoan;
    private Float tonggia;
    private String diachi;
    private String thanhpho;
    private String quanhuyen;
    private String sodienthoai;
    private String phuongxa;
    private String tennguoinhan;

    @ManyToOne
    @JoinColumn(name = "khachhang")
    private KhachHang khachhang;

    @ManyToOne
    @JoinColumn(name = "nhanvien")
    private NhanVien nhanVien;

    public HoaDon() {
    }

    public HoaDon(Integer mahoadon, OffsetDateTime ngaytao, Integer trangthai, String ghichu, OffsetDateTime ngaythanhtoan, Float tonggia, String diachi, String thanhpho, String quanhuyen, String sodienthoai, String phuongxa, String tennguoinhan) {
        this.mahoadon = mahoadon;
        this.ngaytao = ngaytao;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tonggia = tonggia;
        this.diachi = diachi;
        this.thanhpho = thanhpho;
        this.quanhuyen = quanhuyen;
        this.sodienthoai = sodienthoai;
        this.phuongxa = phuongxa;
        this.tennguoinhan = tennguoinhan;

    }
}
