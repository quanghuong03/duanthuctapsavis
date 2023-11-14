package com.example.poloman.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HoaDonChiTietRequest {
    private Integer mahoadon;
    private Integer soluong;
}
