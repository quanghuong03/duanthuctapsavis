package com.example.poloman.model.request;

import lombok.Data;

@Data
public class UpdateTrangThaiGioHang {
    private Integer trangthai;

    public UpdateTrangThaiGioHang(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Integer trangthai() {
        return trangthai;
    }

    public UpdateTrangThaiGioHang() {
    }
}
