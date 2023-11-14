package com.example.poloman.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SanPhamChiTietRequest {
    private Integer mactsp;
    private Integer mamausac;
    private Integer masize;
    private Integer soluongton;
}
