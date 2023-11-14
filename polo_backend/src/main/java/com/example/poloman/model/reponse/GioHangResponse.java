package com.example.poloman.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GioHangResponse {
    private Integer magiohang;
    private List<GioHangChiTietResponse> list;

    public GioHangResponse(Integer magiohang, List<GioHangChiTietResponse> list) {
        this.magiohang = magiohang;
        this.list = list;
    }
}
