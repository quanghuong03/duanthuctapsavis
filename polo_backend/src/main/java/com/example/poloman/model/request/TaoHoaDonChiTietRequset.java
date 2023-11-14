package com.example.poloman.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaoHoaDonChiTietRequset {
    private Integer machitietsanpham;
    private Integer soluong;
    private Double dongia;
}
