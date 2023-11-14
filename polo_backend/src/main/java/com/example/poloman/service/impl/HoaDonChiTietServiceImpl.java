package com.example.poloman.service.impl;

import com.example.poloman.model.entity.HoaDonChiTiet;
import com.example.poloman.model.request.TaoHoaDonChiTietRequset;
import com.example.poloman.repository.ChiTietSanPhamRepository;
import com.example.poloman.repository.HoaDonChiTietRepository;
import com.example.poloman.repository.HoaDonRepository;
import com.example.poloman.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<HoaDonChiTiet> getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet save(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void delete(Integer mahoadon) {
        hoaDonChiTietRepository.deleteById(mahoadon);
    }

    @Override
    public HoaDonChiTiet create(TaoHoaDonChiTietRequset hoaDonChiTietRequset, Integer maHoaDon) {
        var HoaDon = hoaDonRepository.findById(maHoaDon)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found with ID: " + maHoaDon));
        var ChiTietSanPham = chiTietSanPhamRepository.findById(hoaDonChiTietRequset.getMachitietsanpham())
                .orElseThrow(() -> new IllegalArgumentException("Product details not found with ID: " + hoaDonChiTietRequset.getMachitietsanpham()));
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setChiTietSanPham(ChiTietSanPham);
        hoaDonChiTiet.setDongia(hoaDonChiTietRequset.getDongia());
        hoaDonChiTiet.setSoluong(hoaDonChiTietRequset.getSoluong());
        hoaDonChiTiet.setHoadon(HoaDon);
        hoaDonChiTiet.setTrangthai(0);
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet getByMaHoaDon(Integer mahoadon) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(mahoadon).orElseThrow();
        return hoaDonChiTiet;
    }
}
