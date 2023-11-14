package com.example.poloman.service.impl;

import com.example.poloman.model.entity.HoaDon;
import com.example.poloman.model.entity.HoaDonChiTiet;
import com.example.poloman.model.entity.KhachHang;
import com.example.poloman.model.reponse.GioHangChiTietResponse;
import com.example.poloman.model.reponse.GioHangResponse;
import com.example.poloman.model.reponse.HoaDonChiTietResponse;
import com.example.poloman.model.reponse.HoaDonResponse;
import com.example.poloman.model.request.*;
import com.example.poloman.repository.GioHangChiTietRepository;
import com.example.poloman.repository.GioHangRepository;
import com.example.poloman.repository.HoaDonRepository;
import com.example.poloman.repository.KhachHangRepository;
import com.example.poloman.service.GioHangService;
import com.example.poloman.service.HoaDonChiTietService;
import com.example.poloman.service.HoaDonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private GioHangRepository gioHangRepository;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon save(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public void delete(Integer mahoadon) {
        hoaDonRepository.deleteById(mahoadon);
    }

    @Override
    public void hoaDonOnline(HoaDonRequset hoaDonRequset, Integer makhachhang) {
        var now = OffsetDateTime.now();
        var khachhang = khachHangRepository.findById(makhachhang)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + makhachhang));
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTonggia(hoaDonRequset.getTonggia());
        hoaDon.setDiachi(hoaDonRequset.getDiachi());
        hoaDon.setThanhpho(hoaDonRequset.getThanhpho());
        hoaDon.setQuanhuyen(hoaDonRequset.getQuanhuyen());
        hoaDon.setSodienthoai(hoaDonRequset.getSodienthoai());
        hoaDon.setPhuongxa(hoaDonRequset.getPhuongxa());
        hoaDon.setTennguoinhan(hoaDonRequset.getTennguoinhan());
        hoaDon.setGhichu(hoaDonRequset.getGhichu());
        hoaDon.setTrangthai(1);
        hoaDon.setNgaytao(now);
        hoaDon.setKhachhang(khachhang);
        hoaDonRepository.save(hoaDon);

        List<TaoHoaDonChiTietRequset> hoadonchitiet = hoaDonRequset.getHoadonchitiet();
        hoadonchitiet.forEach(request -> hoaDonChiTietService.create(request, hoaDon.getMahoadon()));

        GioHangResponse gioHangResponse = gioHangService.getOneByTrangThai(makhachhang);
        List<GioHangChiTietResponse> list = gioHangResponse.getList();
        list.forEach(giohangct -> gioHangChiTietRepository.deleteById(giohangct.getMagiohangchitiet()));
    }

    @Override
    public List<HoaDonResponse> getHoaDon(Integer makhachhang) {
        List<HoaDonResponse> hoaDonResponses = hoaDonRepository.getHoaDonByKhachHang(makhachhang);
        for (HoaDonResponse hoaDonResponse : hoaDonResponses) {
            List<HoaDonChiTietResponse> hoaDonChiTietResponses = hoaDonRepository.getHoaDonChiTietByHoaDon(hoaDonResponse.getMahoadon());
            hoaDonResponse.setHoaDonChiTietResponses(hoaDonChiTietResponses);
        }
        return hoaDonResponses;
    }

    public List<HoaDonResponse> getHoaDon() {
        List<HoaDonResponse> hoaDonResponses = hoaDonRepository.getAll();
        for (HoaDonResponse hoaDonResponse : hoaDonResponses) {
            List<HoaDonChiTietResponse> hoaDonChiTietResponses = hoaDonRepository.getHoaDonChiTietByHoaDon(hoaDonResponse.getMahoadon());
            hoaDonResponse.setHoaDonChiTietResponses(hoaDonChiTietResponses);
        }
        return hoaDonResponses;
    }

    @Override
    public HoaDonResponse get(Integer mahoadon) {
        var hoaDonResponse = hoaDonRepository.getByIdHoaDon(mahoadon);
        List<HoaDonChiTietResponse> hoaDonChiTietResponses = hoaDonRepository.getHoaDonChiTietByHoaDon(hoaDonResponse.getMahoadon());
        hoaDonResponse.setHoaDonChiTietResponses(hoaDonChiTietResponses);
        return hoaDonResponse;
    }

    @Override
    public void updateHoaDon(UpdateHoaDonRequest updateHoaDonRequest, Integer mahoadon) {
        var hoaDon = hoaDonRepository.findById(mahoadon).orElseThrow();
        hoaDon.setDiachi(updateHoaDonRequest.getDiachi());
        hoaDon.setThanhpho(updateHoaDonRequest.getThanhpho());
        hoaDon.setQuanhuyen(updateHoaDonRequest.getQuanhuyen());
        hoaDon.setSodienthoai(updateHoaDonRequest.getSodienthoai());
        hoaDon.setPhuongxa(updateHoaDonRequest.getPhuongxa());
        hoaDon.setTennguoinhan(updateHoaDonRequest.getTennguoinhan());
        hoaDon.setGhichu(updateHoaDonRequest.getGhichu());
        hoaDonRepository.save(hoaDon);
    }
    @Override
    @Transactional
    public void requestCancel(CancelHoaDonRequest cancelHoaDonRequest, Integer mahoadon) {
        var hoaDon = hoaDonRepository.findById(mahoadon).orElseThrow();
        if (hoaDon.getTrangthai() == 3) {
            hoaDon.setTrangthai(5);
            hoaDon.setGhichu(cancelHoaDonRequest.getGhichu());
            hoaDonRepository.save(hoaDon);
        } else if (hoaDon.getTrangthai() == 1) {
            hoaDon.setTrangthai(4);
            hoaDon.setGhichu(cancelHoaDonRequest.getGhichu());
            hoaDonRepository.save(hoaDon);
        } else {
            throw new IllegalArgumentException("Can't cancel order");
        }
    }

    @Override
    @Transactional
    public void doitrangthai(Integer mahoadon, DoiTrangThaiRequest doiTrangThaiRequest) {
        var now = OffsetDateTime.now();
        var hoadon = hoaDonRepository.findById(mahoadon).orElseThrow();
        switch (doiTrangThaiRequest.getTrangthai()) {
            case 2 -> {
                hoadon.setTrangthai(2);
                hoaDonRepository.save(hoadon);
            }
            case 3 -> {
                hoadon.setTrangthai(3);
                hoaDonRepository.save(hoadon);
            }
            case 6 -> {
                hoadon.setTrangthai(6);
                hoaDonRepository.save(hoadon);
            }
            case 7 -> {
                hoadon.setTrangthai(7);
                hoaDonRepository.save(hoadon);
            }
            case 4 -> {
                if (hoadon.getTrangthai() == 5
                        || hoadon.getTrangthai() == 1
                        || hoadon.getTrangthai() == 6
                        || hoadon.getTrangthai() == 7) {
                    hoadon.setTrangthai(4);
                    hoaDonRepository.save(hoadon);
                } else {
                    throw new IllegalArgumentException("Trạng thái hóa đơn không đúng");
                }
            }
            default -> throw new IllegalArgumentException("Trạng thái hóa đơn không đúng");
        }
    }
}
