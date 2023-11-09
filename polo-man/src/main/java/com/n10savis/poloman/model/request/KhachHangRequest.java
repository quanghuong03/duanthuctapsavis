package com.n10savis.poloman.model.request;

import com.n10savis.poloman.model.entity.KhachHang;
import lombok.Data;

import java.util.Date;
@Data
public class KhachHangRequest {
    private Integer maKhachHang;

    private String tenKhachHang;

    private Date ngaySinh;

    private String soDienThoai;

    private String diaChi;

    private String email;

    private Integer gioiTinh;

    private String matKhau;

    public KhachHang map(KhachHang khachHang){
        khachHang.setTenKhachHang(this.getTenKhachHang());
        khachHang.setNgaySinh(this.getNgaySinh());
        khachHang.setSoDienThoai(this.getSoDienThoai());
        khachHang.setDiaChi(this.getDiaChi());
        khachHang.setEmail(this.getEmail());
        khachHang.setGioiTinh(this.getGioiTinh());
        khachHang.setMatKhau(this.getMatKhau());

        return khachHang;
    }
}
