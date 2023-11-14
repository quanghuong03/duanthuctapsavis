package com.example.poloman.model.request;

import lombok.Data;

@Data
public class UpdateSoLuongGioHang {
    private Integer soluong;

    public UpdateSoLuongGioHang() {
    }

    public UpdateSoLuongGioHang(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer soluong(){
        return soluong;
    }
}
