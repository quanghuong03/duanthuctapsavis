package com.example.poloman.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class UpdateHoaDonRequest {
    private Float tonggia;
    private String diachi;
    private String thanhpho;
    private String quanhuyen;
    private String sodienthoai;
    private String phuongxa;
    private String tennguoinhan;
    private String ghichu;
    private List<HoaDonChiTietRequest> hoadonchitiet;
}
