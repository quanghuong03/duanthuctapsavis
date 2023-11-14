package com.example.poloman.model.reponse;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SanPhamChiTietResponse {
    private Integer mactsp;
    private Integer soluongton;
    private Integer mavach;
    private Integer mamausac;
    private Integer masize;
    private String sosize;
    private String tenmau;

    public SanPhamChiTietResponse() {
    }

    public SanPhamChiTietResponse(Integer mactsp, Integer soluongton, Integer mavach, Integer mamausac, Integer masize, String sosize, String tenmau) {
        this.mactsp = mactsp;
        this.soluongton = soluongton;
        this.mavach = mavach;
        this.mamausac = mamausac;
        this.masize = masize;
        this.sosize = sosize;
        this.tenmau = tenmau;
    }
}
