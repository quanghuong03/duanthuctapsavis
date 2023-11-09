package com.n10savis.poloman.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class KhachHangResponse {
    private Integer maKhachHang;

    private String tenKhachHang;

    private Date ngaySinh;

    private String soDienThoai;

    private String diaChi;

    private String email;

    private Integer gioiTinh;

    public KhachHangResponse(Integer maKhachHang, String tenKhachHang, Date ngaySinh, String soDienThoai, String diaChi, String email, Integer gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.gioiTinh = gioiTinh;
    }
}
