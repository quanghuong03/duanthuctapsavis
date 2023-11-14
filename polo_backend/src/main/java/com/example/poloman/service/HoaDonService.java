package com.example.poloman.service;

import com.example.poloman.model.entity.HoaDon;
import com.example.poloman.model.entity.KhachHang;
import com.example.poloman.model.reponse.HoaDonResponse;
import com.example.poloman.model.request.CancelHoaDonRequest;
import com.example.poloman.model.request.DoiTrangThaiRequest;
import com.example.poloman.model.request.HoaDonRequset;
import com.example.poloman.model.request.UpdateHoaDonRequest;

import java.util.List;

public interface HoaDonService {

    List<HoaDon> getAll();

    HoaDon save(HoaDon hoaDon);

    void delete(Integer mahoadon);

    void hoaDonOnline(HoaDonRequset hoaDonRequset, Integer makhachhang);

    List<HoaDonResponse> getHoaDon(Integer makhachhang);

    public void requestCancel(CancelHoaDonRequest cancelHoaDonRequest, Integer mahoadon);

    void doitrangthai(Integer mahoadon, DoiTrangThaiRequest doiTrangThaiRequest);

    public List<HoaDonResponse> getHoaDon();

    HoaDonResponse get(Integer mahoadon);

   void updateHoaDon(UpdateHoaDonRequest updateHoaDonRequest,Integer mahoadon);
}
