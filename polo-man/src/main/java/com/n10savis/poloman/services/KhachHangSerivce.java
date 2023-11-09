package com.n10savis.poloman.services;

import com.n10savis.poloman.model.entity.KhachHang;
import com.n10savis.poloman.model.request.KhachHangRequest;
import com.n10savis.poloman.model.response.KhachHangResponse;

import java.util.List;

public interface KhachHangSerivce {
    List<KhachHangResponse> getAll();

    KhachHang save(KhachHangRequest khachHangRequest);
}
