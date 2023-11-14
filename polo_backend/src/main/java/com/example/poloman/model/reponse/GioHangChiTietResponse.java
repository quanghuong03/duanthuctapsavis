package com.example.poloman.model.reponse;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.entity.GioHang;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GioHangChiTietResponse {

    private Integer magiohangchitiet;
    private Integer mactsp;
    private String tensanpham;
    private String sosize;
    private String hinhanh;
    private Double giaban;
    private String tenmau;
    private Integer soluong;
    private Integer trangthai;

    public GioHangChiTietResponse(Integer magiohangchitiet, Integer mactsp, String tensanpham, String sosize, String hinhanh, Double giaban, String tenmau, Integer soluong, Integer trangthai) {
        this.magiohangchitiet = magiohangchitiet;
        this.mactsp = mactsp;
        this.tensanpham = tensanpham;
        this.sosize = sosize;
        this.hinhanh = hinhanh;
        this.giaban = giaban;
        this.tenmau = tenmau;
        this.soluong = soluong;
        this.trangthai = trangthai;
    }
}
