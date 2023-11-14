package com.example.poloman.model.reponse;

import com.example.poloman.model.request.TaoHoaDonChiTietRequset;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class HoaDonResponse {
    private Integer mahoadon;
    private Float tonggia;
    private String diachi;
    private String thanhpho;
    private String quanhuyen;
    private String sodienthoai;
    private String phuongxa;
    private String tennguoinhan;
    private String ghichu;
    private List<HoaDonChiTietResponse> hoaDonChiTietResponses;
    private Integer trangthai;


    public HoaDonResponse(Integer mahoadon, Float tonggia, String diachi, String thanhpho, String quanhuyen, String sodienthoai, String phuongxa, String tennguoinhan, String ghichu, Integer trangthai) {
        this.mahoadon = mahoadon;
        this.tonggia = tonggia;
        this.diachi = diachi;
        this.thanhpho = thanhpho;
        this.quanhuyen = quanhuyen;
        this.sodienthoai = sodienthoai;
        this.phuongxa = phuongxa;
        this.tennguoinhan = tennguoinhan;
        this.ghichu = ghichu;
//        this.hoaDonChiTietResponses = hoaDonChiTietResponses;
        this.trangthai = trangthai;
    }
}
