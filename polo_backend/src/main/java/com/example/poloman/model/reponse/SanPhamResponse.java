package com.example.poloman.model.reponse;

import com.example.poloman.model.entity.ChiTietSanPham;
import com.example.poloman.model.entity.SanPham;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SanPhamResponse {
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
    private List<SanPhamChiTietResponse> list;

    public SanPhamResponse(Integer masanpham, Integer mathuonghieu, Integer machatlieu, Integer madongsp, String tensanpham, Double giaban, Double gianhap, String hinhanh, Integer luotmua, String mota, String tenthuonghieu, String tenchatlieu, String tendongsp) {
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

    public SanPhamResponse(SanPham sanPham){
        this.masanpham = sanPham.getMasanpham();
        this.mathuonghieu = sanPham.getThuonghieu().getMathuonghieu();
        this.machatlieu = sanPham.getChatlieu().getMachatlieu();
        this.madongsp = sanPham.getKieuao().getMadongsp();
        this.tensanpham = sanPham.getTensanpham();
        this.giaban = sanPham.getGiaban();
        this.gianhap = sanPham.getGianhap();
        this.hinhanh = sanPham.getHinhanh();
        this.luotmua = sanPham.getLuotmua();
        this.mota = sanPham.getMota();
        this.tenthuonghieu = sanPham.getThuonghieu().getTenthuonghieu();
        this.tenchatlieu = sanPham.getChatlieu().getTenchatlieu();
        this.tendongsp = sanPham.getKieuao().getTendongsp();
    }





}
