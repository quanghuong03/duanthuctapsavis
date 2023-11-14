package com.example.poloman.model.reponse;

import com.example.poloman.model.entity.SanPham;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SanPhamOverviewResponse {
    private Integer masanpham;
    private Integer mathuonghieu;
    private Integer machatlieu;
    private Integer madongsp;
    private String tensanpham;
    private Double giaban;
    private Double gianhap;
    private String hinhanh;
    private Integer luotmua;
    private String mota;
    private String tenthuonghieu;
    private String tenchatlieu;
    private String tendongsp;


    public SanPhamOverviewResponse(Integer masanpham, Integer mathuonghieu, Integer machatlieu, Integer madongsp, String tensanpham, Double giaban, Double gianhap, String hinhanh, Integer luotmua, String mota, String tenthuonghieu, String tenchatlieu, String tendongsp) {
        this.masanpham = masanpham;
        this.mathuonghieu = mathuonghieu;
        this.machatlieu = machatlieu;
        this.madongsp = madongsp;
        this.tensanpham = tensanpham;
        this.giaban = giaban;
        this.gianhap = gianhap;
        this.hinhanh = hinhanh;
        this.luotmua = luotmua;
        this.mota = mota;
        this.tenthuonghieu = tenthuonghieu;
        this.tenchatlieu = tenchatlieu;
        this.tendongsp = tendongsp;
    }

}
