package com.example.poloman.model.request;

import com.example.poloman.model.entity.HoaDon;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class HoaDonRequset {
    private Float tonggia;
    private String diachi;
    private String thanhpho;
    private String quanhuyen;
    private String sodienthoai;
    private String phuongxa;
    private String tennguoinhan;
    private String ghichu;
    private List<TaoHoaDonChiTietRequset> hoadonchitiet;

}
