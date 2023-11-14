package com.example.poloman.model.reponse;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HoaDonChiTietResponse {
    private Integer id;
    private Integer machitietsanpham;
    private Integer masanpham;
    private String tensanpham;
    private String sosize;
    private String tenmau;
    private Integer soluong;
    private Double dongia;
    private String hinhanh;

    public HoaDonChiTietResponse(Integer id, Integer machitietsanpham, Integer masanpham, String tensanpham, String sosize, String tenmau, Integer soluong, Double dongia, String hinhanh) {
        this.id = id;
        this.machitietsanpham = machitietsanpham;
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.sosize = sosize;
        this.tenmau = tenmau;
        this.soluong = soluong;
        this.dongia = dongia;
        this.hinhanh = hinhanh;
    }
}
