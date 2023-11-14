package com.example.poloman.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SanPhamRequest {
    private Integer machatlieu;
    private Integer mathuonghieu;
    private Integer madongsp;
    private String name;
    private Double gianhap;
    private Double giaban;
    private String mota;
    private String hinhanh;
    private String tensanpham;
    List<SanPhamChiTietRequest> sanPhamChiTietRequests;
}
